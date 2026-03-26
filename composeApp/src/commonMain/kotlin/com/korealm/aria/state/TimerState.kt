package com.korealm.aria.state

import androidx.compose.runtime.staticCompositionLocalOf
import com.korealm.aria.utils.Timer

val LocalTimerState = staticCompositionLocalOf<Timer> {
    error("No TimerState provided")
}