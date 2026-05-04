package com.korealm.aria.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korealm.aria.shared.AndroidAudioRepository
import com.korealm.aria.state.PlayerState
import kotlinx.coroutines.launch

class PlayerViewModel: ViewModel() {
    val state = PlayerState(AndroidAudioRepository())

    init {
        viewModelScope.launch {
            state.load()
        }
    }
}