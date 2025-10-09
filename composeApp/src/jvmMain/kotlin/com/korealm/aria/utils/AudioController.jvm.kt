package com.korealm.aria.utils

import com.korealm.aria.model.AudioResource
import korlibs.audio.sound.Sound
import korlibs.audio.sound.readMusic
import korlibs.io.file.std.resourcesVfs
import korlibs.time.infiniteTimes

actual fun provideAudioController(): AudioController = DesktopAudioController()

class DesktopAudioController : AudioController {
    private val sounds = mutableMapOf<AudioResource, Sound>()



    private suspend fun getOrCreateAudio(audio: AudioResource): Sound {
        return sounds.getOrPut(audio) {
            resourcesVfs[audio.audioRes].readMusic()
        }
    }

    override fun load(audio: AudioResource) {
        getOrCreateAudio(audio)
    }

    override fun play(audio: AudioResource) {
        getOrCreateAudio(audio).play(infiniteTimes)

    }

    override fun stop(audio: AudioResource) {
        sounds[audio]?.stop()
    }

    override fun setVolume(audio: AudioResource, volume: Float) {
        sounds[audio]?.volume = volume.toDouble()
    }

    override fun setGlobalVolume(volume: Float) {

    }

}
