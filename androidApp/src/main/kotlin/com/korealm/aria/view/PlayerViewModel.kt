package com.korealm.aria.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.korealm.aria.shared.AndroidAudioRepository
import com.korealm.aria.state.PlayerState
import kotlinx.coroutines.launch

class PlayerViewModel(application: Application): AndroidViewModel(application) {
    val repository = AndroidAudioRepository(application.applicationContext)
    val state = PlayerState(repository)

    // Load all built-in audio resources on app startup
    init {
        viewModelScope.launch {
            state.load()
        }
    }
}