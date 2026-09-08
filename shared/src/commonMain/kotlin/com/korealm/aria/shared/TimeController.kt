package com.korealm.aria.shared

interface TimerController {
    val remainingSeconds: Long
    val isRunning: Boolean

    fun start(seconds: Long)
    fun reset()
}