package com.korealm.aria.shared

import aria.composeapp.generated.resources.Res
import com.korealm.aria.model.AudioResource
import java.util.concurrent.ConcurrentHashMap
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import javax.sound.sampled.FloatControl
import kotlin.math.log10

class JvmAudioController : AudioController {
    private val BASE_AUDIO_VOLUME = 0.8f

    private val clips = ConcurrentHashMap<AudioResource, Clip>()
    private val volumeControls = mutableMapOf<AudioResource, FloatControl>()
    private val perSoundVolume = mutableMapOf<AudioResource, Float>()
    private var globalVolume: Float = BASE_AUDIO_VOLUME

    private suspend fun getOrCreateClip(audio: AudioResource): Clip {
        return clips.getOrPut(audio) {
            val byteStream = Res.readBytes(audio.audioRes).inputStream()
            val audioStream = AudioSystem.getAudioInputStream(byteStream)

            val clip = AudioSystem.getClip()
            clip.open(audioStream)

            val base = perSoundVolume[audio] ?: BASE_AUDIO_VOLUME
            val control = clip.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl
            volumeControls[audio] = control
            internalSetVolume(control, base * globalVolume)

            clip.loop(Clip.LOOP_CONTINUOUSLY)
            clip
        }
    }

    override suspend fun load(audio: AudioResource) {
        getOrCreateClip(audio) // loads and caches
    }

    override suspend fun play(audio: AudioResource) {
        val clip = getOrCreateClip(audio)
        if (!clip.isRunning) clip.start()
    }

    override suspend fun stop(audio: AudioResource) {
        clips[audio]?.let { clip ->
            clip.stop()
            clip.framePosition = 0
        }
    }

    private fun internalSetVolume(control: FloatControl, volume: Float) {
        // Avoid -Infinity for zero volume; map zero/near-zero to control.minimum directly
        val target = if (volume <= 0f) control.minimum else (20f * log10(volume.toDouble())).toFloat()
        val clamped = target.coerceIn(control.minimum, control.maximum)
        // Only write when value actually changes to reduce latency on some mixers
        if (control.value != clamped) {
            control.value = clamped
        }
    }

    override suspend fun setVolume(audio: AudioResource, volume: Float) {
        perSoundVolume[audio] = volume
        volumeControls[audio]?.let { control ->
            val effective = (volume * globalVolume).coerceIn(0f, 1f)
            internalSetVolume(control, effective)
        }
    }

    override suspend fun setGlobalVolume(volume: Float) {
        globalVolume = volume
        // Recompute effective volume for all loaded sounds
        volumeControls.forEach { (res, control) ->
            val base = perSoundVolume[res] ?: BASE_AUDIO_VOLUME
            val effective = (base * globalVolume).coerceIn(0f, 1f)
            internalSetVolume(control, effective)
        }
    }
}
