package com.korealm.aria.shared

import com.korealm.aria.model.AudioResource

// Sounds don't need to be paused. They are either playing or stopped.

interface AudioController {
    fun load(audio: AudioResource)
    fun play(audio: AudioResource)
    fun stop(audio: AudioResource)
    fun setVolume(audio: AudioResource, volume: Float)
    fun setGlobalVolume(volume: Float)
}