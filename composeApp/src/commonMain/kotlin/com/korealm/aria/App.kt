package com.korealm.aria

/* ! IMPORTANT:
 * I have to get the screen size and pixel density to create this app mobile first from the very beginning!
 * Create a ScreenDimensions State or something like that

 TODO: Features that I want to add in the future:
 * Implement settings capabilities: (I'll have to add kotlinx-serialization-json)
     * Desktop: store in a json the app preferences.
     * Web: store in a cookie a json with the app preferences.
 *

*/

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
import com.korealm.aria.state.*
import com.korealm.aria.theme.AppTheme
import com.korealm.aria.ui.Home
import com.korealm.aria.ui.PlayerBar
import com.korealm.aria.ui.components.settings.AboutDialog
import com.korealm.aria.ui.components.settings.PreferencesDialog
import com.korealm.aria.utils.AudioController
import com.korealm.aria.utils.Target.WEB
import com.korealm.aria.utils.getTargetPlatform
import com.korealm.aria.utils.rememberPlayerFacade

@Composable
fun App(
    audioController: AudioController,
    playerState: PlayerState = rememberPlayerState()
) {
    // This DeviceSizeProvider provides LocalDeviceSizeCategory.current
    // which will allow me to change fixed sized UI components, paddings, text...
    DeviceSizeProvider {
        val themeState = rememberAppThemeState()

        val playerState = playerState
        val playerFacade = rememberPlayerFacade(playerState, audioController)

        var isPreferencesDialog by remember { mutableStateOf(false) }
        var isAboutDialog by remember { mutableStateOf(false) }

        AppTheme(darkTheme = themeState.isDarkTheme) {
            val homeWeight = when (getTargetPlatform()) {
                WEB -> if (LocalDeviceSizeCategory.current == DeviceSizeCategory.Mobile) .90f else 0.92f
                else -> 0.89f
            }

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .systemBarsPadding()
            ) {
                Home(
                    playerState = playerState,
                    playerFacade = playerFacade,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(homeWeight),
                )

                PlayerBar(
                    playerState = playerState,
                    playerFacade = playerFacade,
                    onOpenPreferences = { isPreferencesDialog = true },
                    onOpenAbout = { isAboutDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1 - homeWeight),
                )
            }

            // Settings and about dialogs
            AnimatedVisibility(
                visible = isPreferencesDialog,
                enter = fadeIn(animationSpec = tween(durationMillis = 150)) + expandIn(animationSpec = tween(durationMillis = 150)),
                exit = fadeOut(animationSpec = tween(durationMillis = 150)) + shrinkOut(animationSpec = tween(durationMillis = 150))
            ) {
                PreferencesDialog(
                    onDismissRequest = { isPreferencesDialog = false },
                )
            }

            AnimatedVisibility(
                visible = isAboutDialog
            ) {
                AboutDialog(
                    onDismissRequest = { isAboutDialog = false },
                )
            }

        }
    }

}