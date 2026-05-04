package com.korealm.aria.state

import androidx.compose.runtime.*
import com.korealm.aria.model.AudioRepository
import com.korealm.aria.model.Sound
import com.korealm.aria.shared.BuiltInAudioRepository

// This class is the reactive state holder. Any changes in the playlist List will trigger a change.
class PlayerState(
    private val repository: AudioRepository
) {
    var isPlayerActive by mutableStateOf(false)
    var playerVolume by mutableStateOf(1.0) // General volume

    /** The initial list includes all the built-in sounds, but it's mutable, so the user can add more in the future. */
    var playlist = mutableStateListOf<Sound>()
        private set

    suspend fun load() {
        val builtin = repository.loadBuiltIn()
        val user = repository.loadUser()

        playlist.clear()
        playlist.addAll(
            (builtin + user).map { Sound(it) }
        )
    }
}

val LocalPlayerState = staticCompositionLocalOf<PlayerState> { error("No player state provided") }

@Composable
fun rememberPlayerState(repository: AudioRepository = BuiltInAudioRepository()): PlayerState {
    val state = remember { PlayerState(repository) }

    LaunchedEffect(Unit) {
        state.load()
    }

    return state
}