package com.korealm.aria.utils

import com.korealm.aria.model.AudioResource

actual fun provideAudioController(): AudioController = DesktopAudioController()

class DesktopAudioController : AudioController {

    private fun getOrCreateAudio(audio: AudioResource) {

    }

    override fun load(audio: AudioResource) {

    }

    override fun play(audio: AudioResource) {

    }

    override fun stop(audio: AudioResource) {

    }

    override fun setVolume(audio: AudioResource, volume: Float) {

    }

    override fun setGlobalVolume(volume: Float) {

    }
}
