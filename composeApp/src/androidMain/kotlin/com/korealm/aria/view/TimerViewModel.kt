package com.korealm.aria.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korealm.aria.shared.TimerController
import com.korealm.aria.state.Timer

class TimerViewModel : ViewModel(), TimerController {

    private val timer = Timer()

    override val remainingSeconds: Long
        get() = timer.remainingSeconds

    override val isRunning: Boolean
        get() = timer.isRunning

    override fun start(seconds: Long) {
        timer.start(viewModelScope, seconds)
    }

    override fun reset() {
        timer.reset()
    }
}