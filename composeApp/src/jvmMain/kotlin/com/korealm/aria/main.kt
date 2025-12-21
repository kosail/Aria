package com.korealm.aria

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.favicon
import com.korealm.aria.shared.JvmAudioController
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    val icon = painterResource(Res.drawable.favicon)
    val audioController = JvmAudioController()

    Window(
        onCloseRequest = {
            exitApplication()
        },
        title = "Aria",
        icon = icon,
        state = WindowState(size = DpSize(565.dp, 600.dp))
    ) {
        App(audioController)
    }
}