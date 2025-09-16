package com.korealm.aria.utils

import com.korealm.aria.model.AudioResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kuusisto.tinysound.Music
import kuusisto.tinysound.TinySound

actual fun provideAudioController(): AudioController = DesktopAudioController()

class DesktopAudioController : AudioController {
    private val sounds = mutableMapOf<AudioResource, Music>()
    private val scope = CoroutineScope(Dispatchers.Main)

    init {
        TinySound.init()

        // TODO: This will only preload the built-in files, not user added ones. I need to work on finding a better way to handle this
        scope.launch(Dispatchers.IO) {
            AudioResource.entries.forEach { entry ->
                sounds[entry] = TinySound.loadMusic(entry.audioRes, true)
            }
        }
    }

    private fun getOrCreateAudio(audio: AudioResource): Music {
        return sounds.getOrPut(audio) {
            TinySound.loadMusic(audio.audioRes)
        }
    }

    override fun load(audio: AudioResource) {
        getOrCreateAudio(audio)
    }

    override fun play(audio: AudioResource) {
        scope.launch {
            getOrCreateAudio(audio).play(true, 0.5)
        }
    }

    override fun stop(audio: AudioResource) {
        sounds[audio]?.stop()
    }

    override fun setVolume(audio: AudioResource, volume: Float) {
        sounds[audio]?.volume = volume.toDouble()
    }

    override fun setGlobalVolume(volume: Float) {
        TinySound.setGlobalVolume(volume.toDouble())
    }

}
