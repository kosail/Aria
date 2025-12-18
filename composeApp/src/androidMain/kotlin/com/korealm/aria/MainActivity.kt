package com.korealm.aria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.korealm.aria.utils.AndroidAudioController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val audioController = AndroidAudioController(applicationContext)

        setContent {
            val viewModel: PlayerViewModel = viewModel()

            App(
                audioController,
                playerState = viewModel.state
            )
        }
    }
}