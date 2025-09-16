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


@Composable
fun Player(
    playerState: PlayerState,
    playerFacade: PlayerFacade,
    themeState: AppThemeState,
    modifier: Modifier = Modifier
) {
    // This will multiply the sound level of each sound.
    var generalVolumeFactor by remember { mutableStateOf(1f) }
    var isVolumeMenuVisible by remember { mutableStateOf(false) }

    val iconsPadding = when(LocalDeviceSizeCategory.current) {
        DeviceSizeCategory.Mobile -> 16.dp
        else -> 32.dp
    }

    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
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
                    onResetButtonClick = { },
                    volume = generalVolumeFactor,
                    onVolumeChange = { generalVolumeFactor = it },
                    offset = DpOffset((-128).dp, 0.dp),
                    themeState = themeState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }

            PlayerMainIcon(
                isPlaying = playerState.isPlayerActive,
                modifier = Modifier.padding(horizontal = iconsPadding)
            ) { playerFacade.toggleGlobalPlayer() }

            PlayerSecondaryIcon(
                iconRes = Res.drawable.menu,
                contentDescription = Res.string.sounds_menu,
            ) {}
        }
    }
}

