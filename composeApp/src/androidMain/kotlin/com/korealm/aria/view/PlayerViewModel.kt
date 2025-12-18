package com.korealm.aria.view

import androidx.lifecycle.ViewModel
import com.korealm.aria.state.PlayerState

class PlayerViewModel : ViewModel() {
    val state = PlayerState()
}