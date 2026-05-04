package com.korealm.aria.view

import androidx.lifecycle.ViewModel
import com.korealm.aria.shared.BuiltInAudioRepository
import com.korealm.aria.state.PlayerState

class PlayerViewModel : ViewModel() {
    // TODO: Implement the real AndroidAudioRepository
    val state = PlayerState(BuiltInAudioRepository())
}