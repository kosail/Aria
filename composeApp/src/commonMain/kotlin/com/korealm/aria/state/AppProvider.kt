package com.korealm.aria.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.korealm.aria.shared.TimerController
import com.korealm.aria.utils.LocalPlayerFacadeState
import com.korealm.aria.utils.PlayerFacade

@Composable
fun AppProvider(
    playerState: PlayerState,
    playerFacadeState: PlayerFacade,
    timerState: TimerController,
    themeState: AppThemeState,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalThemeState provides themeState,
        LocalPlayerState provides playerState,
        LocalPlayerFacadeState provides playerFacadeState,
        LocalTimerState provides timerState
    ) {
        DeviceSizeProvider {
            content()
        }
    }
}