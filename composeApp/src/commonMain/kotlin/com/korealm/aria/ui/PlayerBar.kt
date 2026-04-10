package com.korealm.aria.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults.rememberTooltipPositionProvider
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import aria.composeapp.generated.resources.*
import com.korealm.aria.state.DeviceSizeCategory.*
import com.korealm.aria.state.LocalDeviceSizeCategory
import com.korealm.aria.state.LocalPlayerState
import com.korealm.aria.state.LocalThemeState
import com.korealm.aria.ui.components.player.PlayerMainIcon
import com.korealm.aria.ui.components.player.PlayerSecondaryIcon
import com.korealm.aria.ui.components.settings.SettingsMenu
import com.korealm.aria.ui.components.volume.VolumeMenu
import com.korealm.aria.utils.LocalPlayerFacadeState
import com.korealm.aria.utils.SimpleTooltipBox


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerBar(
    onOpenTimer: () -> Unit,
    onOpenPreferences: () -> Unit,
    onOpenAbout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val themeState = LocalThemeState.current
    val deviceSizeState = LocalDeviceSizeCategory.current
    val playerState = LocalPlayerState.current
    val playerFacade = LocalPlayerFacadeState.current

    var isVolumeMenu by remember { mutableStateOf(false) }
    var isSettingsMenu by remember { mutableStateOf(false) }

    val iconsPadding = when(deviceSizeState) {
        Mobile -> 16.dp
        else -> 32.dp
    }

    val volumeMenuOffset = when(deviceSizeState) {
        Mobile -> DpOffset((-20).dp, (-1).dp)
        FullDesktop -> DpOffset((-120).dp, 0.dp)
        else -> DpOffset((30).dp, 0.dp)
    }

    val settingsMenuOffset = when(deviceSizeState) {
        Mobile -> DpOffset(20.dp, 1.dp)
        FullDesktop -> DpOffset(0.dp, 0.dp)
        else -> DpOffset((-30).dp, 0.dp)
    }

    val mainIconWeight = when(deviceSizeState) {
        FullDesktop -> 0.2f
        else -> 0.7f
    }

    val bgColor = if (themeState.isDarkTheme) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.primaryContainer

    Surface(
        color = bgColor.copy(0.4f),
        shadowElevation = 1.dp,
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Box {
                    TooltipBox(
                        state = rememberTooltipState(isPersistent = true),
                        tooltip = { SimpleTooltipBox(Res.string.volume_menu) },
                        positionProvider = rememberTooltipPositionProvider(
                            positioning = TooltipAnchorPosition.Below,
                            spacingBetweenTooltipAndAnchor = 4.dp
                        )
                    ) {
                        PlayerSecondaryIcon(
                            iconRes = Res.drawable.volume,
                            contentDescription = Res.string.volume_menu,
                            onClick = { isVolumeMenu = true }
                        )
                    }

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
                        modifier = Modifier
                    )
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(mainIconWeight)
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
                Box {
                    TooltipBox(
                        state = rememberTooltipState(isPersistent = true),
                        tooltip = { SimpleTooltipBox(Res.string.settings_menu) },
                        positionProvider = rememberTooltipPositionProvider(
                            positioning = TooltipAnchorPosition.Below,
                            spacingBetweenTooltipAndAnchor = 4.dp
                        )
                    ) {
                        PlayerSecondaryIcon(
                            iconRes = Res.drawable.menu,
                            contentDescription = Res.string.settings_menu,
                            onClick = { isSettingsMenu = true }
                        )
                    }

                    SettingsMenu(
                        expanded = isSettingsMenu,
                        onDismissRequest = { isSettingsMenu = false },
                        onTimerButton = onOpenTimer,
                        onPreferencesButton = onOpenPreferences,
                        onAboutButton = onOpenAbout,
                        offset = settingsMenuOffset,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

