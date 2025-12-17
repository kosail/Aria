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
import com.korealm.aria.ui.components.volume.VolumeMenu
import com.korealm.aria.utils.PlayerFacade
import com.korealm.aria.utils.darken


@Composable
fun Player(
    playerState: PlayerState,
    playerFacade: PlayerFacade,
    themeState: AppThemeState,
    modifier: Modifier = Modifier
) {
    var isVolumeMenuVisible by remember { mutableStateOf(false) }

    val iconsPadding = when(LocalDeviceSizeCategory.current) {
        DeviceSizeCategory.Mobile -> 16.dp
        else -> 32.dp
    }

    Surface(
        color = MaterialTheme.colorScheme.primaryContainer.copy(0.3f),
        shadowElevation = 1.dp,
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Box {
                PlayerSecondaryIcon(
                    iconRes = Res.drawable.volume,
                    contentDescription = Res.string.volume_menu,
                    onClick = { isVolumeMenuVisible = true }
                )

                VolumeMenu(
                    expanded = isVolumeMenuVisible,
                    onDismissRequest = { isVolumeMenuVisible = false },
                    onResetButtonClick = {
                        playerFacade.stopAll()
                    },
                    volume = playerState.playerVolume,
                    onVolumeChange = {
                        playerState.playerVolume = it
                        playerFacade.setGlobalVolume(playerState.playerVolume)
                    },
                    offset = DpOffset((-128).dp, 0.dp),
                    themeState = themeState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }

            PlayerMainIcon(
                isPlaying = playerState.isPlayerActive,
                iconTint = if (themeState.isDarkTheme) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(horizontal = iconsPadding)
            ) { playerFacade.toggleGlobalPlayer() }

            PlayerSecondaryIcon(
                iconRes = Res.drawable.menu,
                contentDescription = Res.string.sounds_menu,
            ) {}
        }
    }
}

