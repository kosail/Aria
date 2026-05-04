package com.korealm.aria.state

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import kotlinx.coroutines.launch

/**
 * Creates a composable function that provides a launcher for importing audio files.
 * The returned lambda function can be invoked to start the audio import process.
 *
 * @param onResult A suspend function that receives a [Uri] of the selected audio file.
 *                  This function is called after a successful file selection.
 * @return A lambda function that launches the audio importer when invoked.
 */
@Composable
fun rememberAudioImporter(
    onResult: suspend (Uri) -> Unit
): () -> Unit {
    val scope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri != null) {
            scope.launch { onResult(uri) }
        }
    }

    return {
        launcher.launch(arrayOf("audio/*"))
    }
}