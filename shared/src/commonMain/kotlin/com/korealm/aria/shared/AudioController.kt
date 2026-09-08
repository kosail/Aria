package com.korealm.aria.shared

import com.korealm.aria.model.AudioResource

// Sounds don't need to be paused. They are either playing or stopped.

interface AudioController {
    suspend fun play(audio: AudioResource)
    suspend fun stop(audio: AudioResource)
    suspend fun setVolume(audio: AudioResource, volume: Double)
    suspend fun setGlobalVolume(volume: Double)
}