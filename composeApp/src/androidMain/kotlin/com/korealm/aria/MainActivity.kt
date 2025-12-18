package com.korealm.aria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.korealm.aria.view.AudioControllerViewModel
import com.korealm.aria.view.PlayerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val playerView: PlayerViewModel = viewModel()
            val audioControllerView: AudioControllerViewModel = viewModel()

            App(
                audioController = audioControllerView.state,
                playerState = playerView.state
            )
        }
    }
}