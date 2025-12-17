package com.korealm.aria.utils

import com.korealm.aria.model.AudioResource
import kotlinx.browser.document
import org.w3c.dom.HTMLAudioElement

actual fun provideAudioController(): AudioController = WebAudioController()

@OptIn(ExperimentalWasmJsInterop::class)
class WebAudioController : AudioController {
    private val audios = mutableMapOf<AudioResource, HTMLAudioElement>()
    private val perSoundVolume = mutableMapOf<AudioResource, Float>()
    private var globalVolume: Float = 1f

    private fun getOrCreateAudio(audio: AudioResource): HTMLAudioElement {
        return audios.getOrPut(audio) {
            val element = document.createElement("audio") as HTMLAudioElement
            element.src = audio.audioRes
            element.loop = true
            val base = perSoundVolume[audio] ?: 0.5f
            element.volume = (base * globalVolume).toDouble()
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

    override fun stop(audio: AudioResource) {
        audios[audio]?.let { element ->
            element.pause()
            element.currentTime = 0.0
        }
    }

    override fun setVolume(audio: AudioResource, volume: Float) {
        perSoundVolume[audio] = volume
        audios[audio]?.let { element ->
            element.volume = (volume * globalVolume).toDouble()
        }
    }

    override fun setGlobalVolume(volume: Float) {
        globalVolume = volume
        audios.forEach { (res, element) ->
            val base = perSoundVolume[res] ?: 1f
            element.volume = (base * globalVolume).toDouble()
        }
    }
}
