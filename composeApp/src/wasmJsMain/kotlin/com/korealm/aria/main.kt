package com.korealm.aria

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.korealm.aria.utils.WebAudioController
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val audioController = WebAudioController()

    ComposeViewport(document.body!!) {
        App(audioController)
    }
}