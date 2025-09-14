package com.korealm.aria.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.korealm.aria.model.Sound
import com.korealm.aria.state.PlayerState

// This class is the one which binds the state holder PlayerState with the low implementations in AudioController
class PlayerFacade(
    private val state: PlayerState,
    private val controller: AudioController
) {

    fun play(sound: Sound) {
        controller.play(sound.resource)
        updateSound(sound) { it.copy(isPlaying = true) }
        state.isPlayerActive = true
    }

    fun stop(sound: Sound) {
        controller.stop(sound.resource)
        updateSound(sound) { it.copy(isPlaying = false) }
        state.isPlayerActive = state.playlist.any { it.isPlaying }
    }

    /**
     * This function works interacting with sounds that are selected/enabled by the user.
     * It just stops the audio without touching if they are selected or not.
     * In that way, the user can click the play button and get all the last playlist sounds back.
     * */
    fun toggleGlobalPlayer() {
        val action: (Sound) -> Unit =  { sound ->
            if (state.isPlayerActive) stop(sound) else play(sound)
        }
        state.playlist.filter { it.isSelected }.forEach(action)
    }

    fun setGlobalVolume(volume: Float) {
        controller.setGlobalVolume(volume)
        state.playerVolume = volume
    }

    private fun updateSound(sound: Sound, update: (Sound) -> Sound) {
        val index = state.playlist.indexOf(sound)

        if (index != -1) state.playlist[index] = update(state.playlist[index])
    }
}

@Composable
fun rememberPlayerFacade(
    state: PlayerState,
    controller: AudioController
): PlayerFacade = remember { PlayerFacade(state, controller) }