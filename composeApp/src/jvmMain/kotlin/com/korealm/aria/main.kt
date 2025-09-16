package com.korealm.aria

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.favicon
import kuusisto.tinysound.TinySound
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    val icon = painterResource(Res.drawable.favicon)

    Window(
        onCloseRequest = {
            TinySound.shutdown() // Shutdown tinysound, or else it will cause tinysound threads to hang
            exitApplication()
        },
        title = "Aria",
        icon = icon,
        state = WindowState(size = DpSize(1100.dp, 700.dp))
    ) {
        App()
    }
}