package com.korealm.aria

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.korealm.aria.shared.JvmAudioController
import com.korealm.aria.state.PlayerState
import com.korealm.aria.state.rememberPlayerState

fun main() = application {
    val icon = getAriaWindowIcon()
    val audioController = JvmAudioController()
    val playerState = rememberPlayerState()

    Window(
        onCloseRequest = {
            exitApplication()
        },
        title = "Aria",
        icon = icon,
        state = WindowState(size = DpSize(565.dp, 650.dp))
    ) {
        App(audioController, playerState)
    }

    LaunchedEffect(Unit) {
        preloadAllSounds(audioController, playerState)
    }
}

suspend fun preloadAllSounds(controller: JvmAudioController, playerState: PlayerState) {
    playerState.playlist.forEach { audio ->
        controller.warmup(audio.resource)
    }
}