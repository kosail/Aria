package com.korealm.aria.state

import androidx.compose.runtime.*
import com.korealm.aria.data.CustomSoundIcons
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

    /*
     * Repository functions are to update or delete from persistent data, while the functions below are bindings to perform changes both in the already Audios (in-memory playlist), and in disk (persistent one).
     *
     * I set the repository to private to only expose the functions below and don't use one for another by mistake.
     */

    suspend fun updateTitle(id: Int, newTitle: String) {
        repository.updateTitle(id, newTitle)

        val index = playlist.indexOfFirst { it.resource.id == id }
        if (index == -1) return

        val sound = playlist[index]
        val updated = sound.copy(
            resource = sound.resource.copy(title = newTitle)
        )

        playlist[index] = updated
    }

    suspend fun updateIcon(id: Int, icon: CustomSoundIcons) {
        repository.updateIcon(id, icon)

        val index = playlist.indexOfFirst { it.resource.id == id }
        if (index == -1) return

        val sound = playlist[index]
        val updated = sound.copy(
            resource = sound.resource.copy(icon = icon.icon)
        )

        playlist[index] = updated
    }

    suspend fun deleteUserSound(id: Int) {
        repository.deleteUserSound(id)

        val index = playlist.indexOfFirst { it.resource.id == id }
        if (index == -1) return

        playlist.removeAt(index)
    }

    suspend fun deleteAllUserSounds() {
        repository.deleteAllUserSounds()

        val builtIn = repository.loadBuiltIn().map { Sound(it) }

        playlist.clear()
        playlist.addAll(builtIn)
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