package com.korealm.aria

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.favicon
import com.korealm.aria.model.AudioResource
import com.korealm.aria.shared.JvmAudioController
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    val icon = painterResource(Res.drawable.favicon)
    val audioController = JvmAudioController()

    val scope = rememberCoroutineScope()
    scope.launch { preloadAllSounds(audioController) }

    Window(
        onCloseRequest = {
            exitApplication()
        },
        title = "Aria",
        icon = icon,
        state = WindowState(size = DpSize(565.dp, 650.dp))
    ) {
        App(audioController)
    }
}

suspend fun preloadAllSounds(controller: JvmAudioController) {
    AudioResource.entries.forEach { audio ->
        controller.warmup(audio)
    }
}