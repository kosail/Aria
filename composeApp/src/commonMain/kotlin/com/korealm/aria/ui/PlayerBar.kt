package com.korealm.aria.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import aria.composeapp.generated.resources.*
import com.korealm.aria.state.AppThemeState
import com.korealm.aria.state.DeviceSizeCategory
import com.korealm.aria.state.LocalDeviceSizeCategory
import com.korealm.aria.state.PlayerState
import com.korealm.aria.ui.components.player.PlayerMainIcon
import com.korealm.aria.ui.components.player.PlayerSecondaryIcon
import com.korealm.aria.ui.components.settings.SettingsMenu
import com.korealm.aria.ui.components.volume.VolumeMenu
import com.korealm.aria.utils.PlayerFacade
import com.korealm.aria.utils.Target
import com.korealm.aria.utils.getTargetPlatform


@Composable
fun PlayerBar(
    playerState: PlayerState,
    playerFacade: PlayerFacade,
    themeState: AppThemeState,
    modifier: Modifier = Modifier
) {
    var isVolumeMenu by remember { mutableStateOf(false) }
    var isSettingsMenu by remember { mutableStateOf(false) }

    val iconsPadding = when(LocalDeviceSizeCategory.current) {
        DeviceSizeCategory.Mobile -> 16.dp
        else -> 32.dp
    }

    val volumeMenuOffset = when(getTargetPlatform()) {
        Target.ANDROID -> DpOffset((-20).dp, (-1).dp)
        else -> DpOffset((-128).dp, 0.dp)
    }

    val settingsMenuOffset = when(getTargetPlatform()) {
        Target.ANDROID -> DpOffset(20.dp, 1.dp)
        else -> DpOffset(128.dp, 0.dp)
    }

    Surface(
        color = MaterialTheme.colorScheme.primaryContainer.copy(0.3f),
        shadowElevation = 1.dp,
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Box {
                    PlayerSecondaryIcon(
                        iconRes = Res.drawable.volume,
                        contentDescription = Res.string.volume_menu,
                        onClick = { isVolumeMenu = true }
                    )

                    VolumeMenu(
                        expanded = isVolumeMenu,
                        onDismissRequest = { isVolumeMenu = false },
                        onResetButton = {
                            playerFacade.stopAll()
                            isVolumeMenu = false
                        },
                        volume = playerState.playerVolume,
                        onVolumeChange = {
                            playerState.playerVolume = it
                            playerFacade.setGlobalVolume(playerState.playerVolume)
                        },
                        offset = volumeMenuOffset,
                        themeState = themeState,
                        modifier = Modifier
                    )
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(0.7f)
                    .align(Alignment.CenterVertically)
            ) {
                PlayerMainIcon(
                    isPlaying = playerState.isPlayerActive,
                    iconTint = if (themeState.isDarkTheme) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.background,
                    modifier = Modifier.padding(horizontal = iconsPadding)
                ) { playerFacade.toggleGlobalPlayer() }
            }

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Box{
                    PlayerSecondaryIcon(
                        iconRes = Res.drawable.menu,
                        contentDescription = Res.string.settings_menu,
                        onClick = { isSettingsMenu = true }
                    )

                    SettingsMenu(
                        expanded = isSettingsMenu,
                        onDismissRequest = { isSettingsMenu = false },
                        onPreferencesButton = {  },
                        onAboutButton = {  },
                        offset = settingsMenuOffset,
                        themeState = themeState,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

