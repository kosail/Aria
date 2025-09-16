package com.korealm.aria

/* ! IMPORTANT:
 * I have to get the screen size and pixel density to create this app mobile first from the very beginning!
 * Create a ScreenDimensions State or something like that

 TODO: Features that I want to add in the future:
 * Enable/Disable animated background to have a plain background, like I did in Kvantage.
 * Implement settings capabilities: (I'll have to add kotlinx-serialization-json)
     * Desktop: store in a json the app preferences.
     * Web: store in a cookie a json with the app preferences.
 *

*/

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.korealm.aria.state.*
import com.korealm.aria.theme.WhisperingNatureTheme
import com.korealm.aria.ui.Home
import com.korealm.aria.ui.Player
import com.korealm.aria.utils.Target.DESKTOP
import com.korealm.aria.utils.Target.WEB
import com.korealm.aria.utils.getTargetPlatform
import com.korealm.aria.utils.provideAudioController
import com.korealm.aria.utils.rememberPlayerFacade
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    // This DeviceSizeProvider provides LocalDeviceSizeCategory.current
    // which will allow me to change fixed sized UI components, paddings, text...
    DeviceSizeProvider {
        val themeState = rememberAppThemeState()
        val playerState = rememberPlayerState()
        val playerFacade = rememberPlayerFacade(playerState, provideAudioController())

        WhisperingNatureTheme(darkTheme = themeState.isDarkTheme) {
            val homeWeight = when (getTargetPlatform()) {
                DESKTOP -> 0.89f
                WEB -> if (LocalDeviceSizeCategory.current == DeviceSizeCategory.Mobile) .90f else 0.92f
            }

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Home(
                    playerState = playerState,
                    playerFacade = playerFacade,
                    themeState = themeState,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(homeWeight),
                )

                Player(
                    playerState = playerState,
                    playerFacade = playerFacade,
                    themeState = themeState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1 - homeWeight),
                )
            }
        }
    }

}