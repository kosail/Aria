package com.korealm.aria.utils

import com.korealm.aria.model.AudioResource
import com.korealm.aria.model.Sound
import kotlinx.browser.document
import org.w3c.dom.HTMLAudioElement

actual fun provideAudioController(): AudioController = WebAudioController()

class WebAudioController : AudioController {
    private val audios = mutableMapOf<AudioResource, HTMLAudioElement>()

    private fun getOrCreateAudio(audio: AudioResource): HTMLAudioElement {
        return audios.getOrPut(audio) {
            val element = document.createElement("audio") as HTMLAudioElement
            element.src = audio.audioRes
            element.loop = true
            element.volume = 0.5
            element
        }
    }

    override fun load(audio: AudioResource) {
        getOrCreateAudio(audio).load()
    }

    override fun play(audio: AudioResource) {
        val element = getOrCreateAudio(audio)
        element.play()
    }

    override fun pause(audio: AudioResource) {
        audios[audio]?.pause()
    }

    override fun stop(audio: AudioResource) {
        audios[audio]?.let { element ->
            element.pause()
            element.currentTime = 0.0
        }
    }

    override fun setVolume(audio: AudioResource, volume: Float) {
        audios[audio]?.let { element ->
            element.volume = volume.toDouble()
        }
    }

    override fun setGlobalVolume(volume: Float) {
        audios.values.forEach { it.volume = volume.toDouble() }
    }
}
