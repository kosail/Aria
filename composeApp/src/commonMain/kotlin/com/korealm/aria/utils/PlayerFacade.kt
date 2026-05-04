package com.korealm.aria.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.korealm.aria.model.Sound
import com.korealm.aria.shared.AudioController
import com.korealm.aria.state.PlayerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * A facade class that provides a unified interface for controlling audio playback and player state.
 *
 * @property state The reactive state holder for the audio player.
 * @property controller The controller interface responsible for manipulating audio resources.
 * @property scope Coroutine scope used to execute asynchronous tasks.
 */
class PlayerFacade(
    private val state: PlayerState,
    private val controller: AudioController,
    private val scope: CoroutineScope
) {
    fun play(sound: Sound) {
        updateSound(sound) { it.copy(isPlaying = true) }
        scope.launch {
            controller.play(sound.resource)
            state.isPlayerActive = true
        }
    }

    fun stop(sound: Sound) {
        updateSound(sound) { it.copy(isPlaying = false) }
        scope.launch {
            controller.stop(sound.resource)
            state.isPlayerActive = state.playlist.any { it.isPlaying }
        }
    }

    fun setVolume(sound: Sound, volume: Double) {
        updateSound(sound) { it.copy(volume = volume) }
        scope.launch {
            controller.setVolume(sound.resource, volume)
        }
    }

    fun stopAll() {
        state.playlist.forEach { sound ->
            updateSound(sound) { it.copy(isSelected = false) }
            stop(sound)
        }
    }

    /**
     * This function works by interacting with sounds that are selected/enabled by the user.
     * It just stops the audio without touching if they are selected or not.
     * In that way, the user can click the play button and get all the last playlist sounds back.
     * */
    fun toggleGlobalPlayer() {
        val action: (Sound) -> Unit =  { sound ->
            if (state.isPlayerActive) stop(sound) else play(sound)
        }
        state.playlist.filter { it.isSelected }.forEach(action)
    }

    fun setGlobalVolume(volume: Double) {
        state.playerVolume = volume
        scope.launch {
            controller.setGlobalVolume(volume)
        }
    }

    private fun updateSound(sound: Sound, update: (Sound) -> Sound) {
        val index = state.playlist.indexOf(sound)

        if (index != -1) state.playlist[index] = update(state.playlist[index])
    }
}

val LocalPlayerFacadeState = staticCompositionLocalOf<PlayerFacade> { error("No player facade provided") }

@Composable
fun rememberPlayerFacade(
    state: PlayerState,
    controller: AudioController,
    coroutineScope: CoroutineScope
): PlayerFacade = remember { PlayerFacade(state, controller, coroutineScope) }