package com.korealm.aria.state

import androidx.compose.runtime.*
import com.korealm.aria.model.AudioResource
import com.korealm.aria.model.Sound

// This class is the reactive state holder. Any changes in the playlist List will trigger a change.
class PlayerState {
    var isPlayerActive by mutableStateOf(false)
    var playerVolume by mutableStateOf(1f)

    /** The initial list includes all the built-in sounds, but it's mutable, so the user can add more in the future. */
    var playlist = mutableStateListOf<Sound>(
        Sound(AudioResource.RAIN),
        Sound(AudioResource.STORM),
        Sound(AudioResource.WIND),
        Sound(AudioResource.WAVES),
        Sound(AudioResource.STREAM),
        Sound(AudioResource.BIRDS),
        Sound(AudioResource.SUMMER_NIGHT),
        Sound(AudioResource.TRAIN),
        Sound(AudioResource.BOAT),
        Sound(AudioResource.CITY),
        Sound(AudioResource.COFFEE_SHOP),
        Sound(AudioResource.FIREPLACE),
        Sound(AudioResource.PINK_NOISE),
        Sound(AudioResource.WHITE_NOISE)
    )
}

@Composable
fun rememberPlayerState(): PlayerState = remember { PlayerState() }