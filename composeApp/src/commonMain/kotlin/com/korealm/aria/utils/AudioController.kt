package com.korealm.aria.utils

import com.korealm.aria.model.AudioResource

// Sounds don't need to be paused. They are either playing or stopped.

interface AudioController {
    /** Load the audio resource into the player */
    fun load(audio: AudioResource)
    /** Used by the global play/pause state. */
    fun play(audio: AudioResource)
    /** Used by the global play/pause state. */
    fun pause(audio: AudioResource)
    /** Used by individual sounds */
    fun stop(audio: AudioResource)
    /** Used by individual sounds */
    fun setVolume(volume: Float)
    /** Used by global volume state */
    fun setGlobalVolume(volume: Float)
}

// Platform resolver
expect fun provideAudioController(): AudioController