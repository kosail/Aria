package com.korealm.aria.utils

import com.korealm.aria.model.Sound
import com.korealm.aria.state.PlayerState

fun updateSound(
    sound: Sound,
    playerState: PlayerState,
    update: (Sound) -> Sound,
) {
    val index = playerState.playlist.indexOf(sound)

    if (index != -1) playerState.playlist[index] = update(playerState.playlist[index])
}