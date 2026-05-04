package com.korealm.aria.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.aria
import com.korealm.aria.shared.Target.*
import com.korealm.aria.shared.getTargetPlatform
import com.korealm.aria.state.DeviceSizeCategory.*
import com.korealm.aria.state.LocalDeviceSizeCategory
import com.korealm.aria.state.LocalPlayerState
import com.korealm.aria.state.LocalThemeState
import com.korealm.aria.ui.components.home.AddSoundCard
import com.korealm.aria.ui.components.home.SoundCard
import com.korealm.aria.ui.components.misc.AriaTitleFont
import com.korealm.aria.utils.LocalPlayerFacadeState
import org.jetbrains.compose.resources.stringResource

/**
 * Composable function that represents the Home screen of the application.
 * Displays a grid of sound cards, allowing users to play, stop, and adjust the volume of sounds.
 * Additionally, provides an option to add new sounds on Android platforms.
 *
 * @param onAddSound Optional callback invoked when the "Add Sound" button is pressed on Android.
 * @param modifier Modifier to be applied to the Home composable.
 */
@Composable
fun Home(
    onAddSound: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val themeState = LocalThemeState.current
    val deviceSizeState = LocalDeviceSizeCategory.current
    val playerState = LocalPlayerState.current
    val playerFacade = LocalPlayerFacadeState.current

    val mainSurfacePadding = when (deviceSizeState) {
        Mobile -> 10.dp
        CompactDesktop -> 40.dp
        FullDesktop -> 50.dp
    }

    Box(
        modifier = modifier
            .padding(horizontal = mainSurfacePadding)
            .padding(top = mainSurfacePadding, bottom = 5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // Only show the title of the app if it is being run as web or mobile app.
            if (getTargetPlatform() != DESKTOP) {
                Box(
                    contentAlignment = if (deviceSizeState == Mobile) Alignment.Center else Alignment.CenterStart,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AriaTitleFont(
                        text = stringResource(Res.string.aria),
                        fontSize = 72.sp
                    )
                }
            }


            val soundCardPadding = when (deviceSizeState) {
                Mobile -> 12.dp
                CompactDesktop -> 18.dp
                FullDesktop -> 24.dp
            }


            val soundCardWidth = when (deviceSizeState) {
                Mobile -> 124.dp
                else -> if(getTargetPlatform() == WEB) 216.dp else 170.dp
            }

            LazyVerticalGrid (
                columns = GridCells.Adaptive(minSize = soundCardWidth),
                horizontalArrangement = Arrangement.spacedBy(soundCardPadding / 2),
                verticalArrangement = Arrangement.spacedBy(soundCardPadding / 2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {
                playerState.playlist.forEach { sound ->
                    item {
                        SoundCard(
                            themeState = themeState,
                            sound = sound,
                            cardSize = soundCardWidth,
                            onVolumeChange = { newVolume ->
                                playerFacade.setVolume(sound, newVolume)
                            }
                        ) {
                            val updated = sound.copy(isSelected = !sound.isSelected)
                            val index = playerState.playlist.indexOf(sound)

                            if (index != -1) playerState.playlist[index] = updated

                            if (updated.isSelected) playerFacade.play(updated) else playerFacade.stop(updated)
                        }
                    }

                }
                if (getTargetPlatform() == ANDROID) {
                    item {
                        AddSoundCard(
                            themeState = themeState,
                            cardSize = soundCardWidth
                        ) {
                            onAddSound?.invoke()
                        }
                    }
                }
            }
        }
    }
}