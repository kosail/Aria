package com.korealm.aria.model

data class Sound(
    val resource: AudioResource,
    val isPlaying: Boolean = false,
    val isSelected: Boolean = false,
    val volume: Float = 0.8f
)
