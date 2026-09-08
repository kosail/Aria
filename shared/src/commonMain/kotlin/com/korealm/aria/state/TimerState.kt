package com.korealm.aria.state

import androidx.compose.runtime.*
import com.korealm.aria.shared.TimerController
import com.korealm.aria.utils.DefaultTimerController
import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.milliseconds

class Timer {

    private var job: Job? = null

    var remainingSeconds by mutableStateOf(0L)
        private set

    var isRunning by mutableStateOf(false)
        private set

    fun start(scope: CoroutineScope, seconds: Long) {
        job?.cancel()
        remainingSeconds = seconds
        isRunning = true

        job = scope.launch {
            while (remainingSeconds > 0 && isActive) {
                delay(1000.milliseconds)
                remainingSeconds--
            }
            isRunning = false
        }
    }

    fun reset() {
        job?.cancel()
        remainingSeconds = 0
        isRunning = false
    }
}

val LocalTimerState = staticCompositionLocalOf<TimerController> {
    error("No TimerState provided")
}

@Composable
fun rememberTimerController(): TimerController {
    val scope = rememberCoroutineScope()
    return remember { DefaultTimerController(scope) }
}