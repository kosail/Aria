package com.korealm.aria.utils

import com.korealm.aria.model.AudioResource
import java.io.File
import javax.sound.sampled.*
import kotlin.math.log10

actual fun provideAudioController(): AudioController = JvmAudioController()

class JvmAudioController : AudioController {
    private val clips = mutableMapOf<AudioResource, Clip>()

    private fun getOrCreateClip(audio: AudioResource): Clip {
        return clips.getOrPut(audio) {
            val audioFile = File(javaClass.classLoader.getResource(audio.audioRes)!!.toURI())
            val inputStream = AudioSystem.getAudioInputStream(audioFile)
            val clip = AudioSystem.getClip()
            clip.open(inputStream)
            clip.loop(Clip.LOOP_CONTINUOUSLY)
            internalSetVolume(clip, 0.5f)
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

    private fun internalSetVolume(clip: Clip, volume: Float) {
        val control = clip.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl
        val dB = (log10(volume.toDouble()) * 20).toFloat()
        control.value = dB.coerceIn(control.minimum, control.maximum)
    }

    override fun setVolume(audio: AudioResource, volume: Float) {
        clips[audio]?.let { clip ->
            internalSetVolume(clip, volume)
        }
    }

    override fun setGlobalVolume(volume: Float) {
        clips.values.forEach { clip ->
            internalSetVolume(clip, volume)
        }
    }
}
