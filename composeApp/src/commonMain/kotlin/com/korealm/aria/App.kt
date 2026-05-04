package com.korealm.aria

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.korealm.aria.shared.AudioController
import com.korealm.aria.shared.Target.WEB
import com.korealm.aria.shared.TimerController
import com.korealm.aria.shared.getTargetPlatform
import com.korealm.aria.state.*
import com.korealm.aria.state.DeviceSizeCategory.Mobile
import com.korealm.aria.theme.AppTheme
import com.korealm.aria.ui.Home
import com.korealm.aria.ui.PlayerBar
import com.korealm.aria.ui.components.settings.iconPicker.IconPickerDialog
import com.korealm.aria.ui.components.settings.about.AboutDialog
import com.korealm.aria.ui.components.settings.preferences.PreferencesDialog
import com.korealm.aria.ui.components.settings.timer.TimerDialog
import com.korealm.aria.utils.rememberPlayerFacade

/**
 * The main application entry point providing the structure and theme of the app.
 *
 * @param audioController The controller responsible for handling audio playback operations.
 * @param playerState The current state of the audio player. Defaults to a remembered player state.
 * @param timerState The controller managing the timer's state. Defaults to a remembered timer controller.
 * @param onAddSound An optional function to invoke when adding a new sound. Defaults to null, currently injected only by the Android target.
 */
@Composable
fun App(
    audioController: AudioController,
    playerState: PlayerState = rememberPlayerState(),
    timerState: TimerController = rememberTimerController(),
    onAddSound: (() -> Unit)? = null // No need of an interface because is one single function
) {
    val themeState = rememberAppThemeState()
    val coroutineScope = rememberCoroutineScope() // Needed to perform audio IO from the player facade
    val playerFacadeState = rememberPlayerFacade(playerState, audioController, coroutineScope)

    AppProvider(playerState, playerFacadeState, timerState, themeState) {
        val deviceSizeState = LocalDeviceSizeCategory.current

        var isTimerDialog by remember { mutableStateOf(false) }
        var isPreferencesDialog by remember { mutableStateOf(false) }
        var isAboutDialog by remember { mutableStateOf(false) }
        var isIconPickerDialog by remember { mutableStateOf(false) } // TODO: set to true just for development

        AppTheme(
            darkTheme = themeState.isDarkTheme,
            accentColor = themeState.accentColor
        ) {
            val homeWeight = when (getTargetPlatform()) {
                WEB -> if (deviceSizeState == Mobile) .90f else 0.92f
                else -> 0.89f
            }

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .systemBarsPadding()
            ) {
                Home(
                    onAddSound = onAddSound,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(homeWeight),
                )

                PlayerBar(
                    onOpenTimer = { isTimerDialog = true },
                    onOpenPreferences = { isPreferencesDialog = true },
                    onOpenAbout = { isAboutDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1 - homeWeight),
                )
            }

            // Dialogs
            AnimatedVisibility(
                visible = isPreferencesDialog,
                enter = fadeIn(animationSpec = tween(durationMillis = 150)) + expandIn(animationSpec = tween(durationMillis = 150)),
                exit = fadeOut(animationSpec = tween(durationMillis = 150)) + shrinkOut(animationSpec = tween(durationMillis = 150))
            ) {
                PreferencesDialog { isPreferencesDialog = false }
            }

            AnimatedVisibility(
                visible = isAboutDialog,
                enter = fadeIn(animationSpec = tween(durationMillis = 150)) + expandIn(animationSpec = tween(durationMillis = 150)),
                exit = fadeOut(animationSpec = tween(durationMillis = 150)) + shrinkOut(animationSpec = tween(durationMillis = 150))
            ) {
                AboutDialog { isAboutDialog = false }
            }

            AnimatedVisibility(
                visible = isTimerDialog,
                enter = fadeIn(animationSpec = tween(durationMillis = 150)) + expandIn(animationSpec = tween(durationMillis = 150)),
                exit = fadeOut(animationSpec = tween(durationMillis = 150)) + shrinkOut(animationSpec = tween(durationMillis = 150))
            ) {
                TimerDialog { isTimerDialog = false }
            }

            AnimatedVisibility(
                visible = isIconPickerDialog,
                enter = fadeIn(animationSpec = tween(durationMillis = 150)) + expandIn(animationSpec = tween(durationMillis = 150)),
                exit = fadeOut(animationSpec = tween(durationMillis = 150)) + shrinkOut(animationSpec = tween(durationMillis = 150))
            ) {
                IconPickerDialog { isIconPickerDialog = false }
            }

        }

        // I found no other way to bind the timer to the player state, although I know there must be a cleaner way to do it.
        LaunchedEffect(timerState.remainingSeconds) {
            val remaining = timerState.remainingSeconds

            if (timerState.isRunning && remaining in 0..15) {
                val volume = remaining / 17.0
                playerFacadeState.setGlobalVolume(volume)
            }

            if (remaining == 0L) {
                playerFacadeState.stopAll()
                playerFacadeState.setGlobalVolume(1.0)
            }
        }
    }
}