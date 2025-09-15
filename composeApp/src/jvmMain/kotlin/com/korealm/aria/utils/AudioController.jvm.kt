package com.korealm.aria.utils

import com.korealm.aria.model.AudioResource

actual fun provideAudioController(): AudioController = DesktopAudioController()

class DesktopAudioController : AudioController {
    override fun load(audio: AudioResource) { println("DesktopAudioController loading sound ${audio.titleRes}") }

    override fun play(audio: AudioResource) { println("DesktopAudioController playing sound ${audio.titleRes}") }

    override fun pause(audio: AudioResource) { println("DesktopAudioController paused all sounds") }

    override fun stop(audio: AudioResource) { println("DesktopAudioController stopping sound ${audio.titleRes}") }

    override fun setVolume(audio: AudioResource, volume: Float) { println("DesktopAudioController setVolume to $volume") }

    override fun setGlobalVolume(volume: Float) { println("DesktopAudioController setGlobalVolume $volume") }

}
