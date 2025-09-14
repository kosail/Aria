package com.korealm.aria.utils

import com.korealm.aria.model.AudioResource
import com.korealm.aria.model.Sound

actual fun provideAudioController(): AudioController = WebAudioController()

class WebAudioController : AudioController {
    override fun load(audio: AudioResource) {}

    override fun play(audio: AudioResource) {}

    override fun pause(audio: AudioResource) {}

    override fun stop(audio: AudioResource) {}

    override fun setVolume(volume: Float) {}

    override fun setGlobalVolume(volume: Float) {}

}
