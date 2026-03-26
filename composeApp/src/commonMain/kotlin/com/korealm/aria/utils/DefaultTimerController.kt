package com.korealm.aria.utils

import com.korealm.aria.shared.TimerController
import com.korealm.aria.state.Timer
import kotlinx.coroutines.CoroutineScope

class DefaultTimerController(
    private val scope: CoroutineScope
) : TimerController {

    private val timer = Timer()

    override val remainingSeconds: Long
        get() = timer.remainingSeconds

    override val isRunning: Boolean
        get() = timer.isRunning

    override fun start(seconds: Long) {
        timer.start(scope, seconds)
    }

    override fun reset() {
        timer.reset()
    }
}