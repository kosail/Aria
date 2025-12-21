package com.korealm.aria.shared

import com.korealm.aria.model.AudioResource
import com.korealm.aria.shared.AudioController
import java.io.File
import javax.sound.sampled.*
import kotlin.math.log10

class JvmAudioController : AudioController {
    private val clips = mutableMapOf<AudioResource, Clip>()
    private val volumeControls = mutableMapOf<AudioResource, FloatControl>()
    private val perSoundVolume = mutableMapOf<AudioResource, Float>()
    private var globalVolume: Float = 1f

    private fun getOrCreateClip(audio: AudioResource): Clip {
        return clips.getOrPut(audio) {
            val audioFile = File(javaClass.classLoader.getResource(audio.audioRes)!!.toURI())
            val inputStream = AudioSystem.getAudioInputStream(audioFile)
            val clip = AudioSystem.getClip()
            clip.open(inputStream)
            clip.loop(Clip.LOOP_CONTINUOUSLY)

            val base = perSoundVolume[audio] ?: 0.8f
            val control = clip.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl
            volumeControls[audio] = control
            internalSetVolume(control, base * globalVolume)
            clip
        }
    }

    override fun load(audio: AudioResource) {
        getOrCreateClip(audio) // loads and caches
    }

    override fun play(audio: AudioResource) {
        val clip = getOrCreateClip(audio)
        if (!clip.isRunning) clip.start()
    }

    override fun stop(audio: AudioResource) {
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

    override fun setVolume(audio: AudioResource, volume: Float) {
        perSoundVolume[audio] = volume
        volumeControls[audio]?.let { control ->
            val effective = (volume * globalVolume).coerceIn(0f, 1f)
            internalSetVolume(control, effective)
        }
    }

    override fun setGlobalVolume(volume: Float) {
        globalVolume = volume
        // Recompute effective volume for all loaded sounds
        volumeControls.forEach { (res, control) ->
            val base = perSoundVolume[res] ?: 1f
            val effective = (base * globalVolume).coerceIn(0f, 1f)
            internalSetVolume(control, effective)
        }
    }
}
