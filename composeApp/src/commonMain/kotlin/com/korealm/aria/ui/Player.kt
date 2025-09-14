package com.korealm.aria.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import aria.composeapp.generated.resources.*
import com.korealm.aria.state.DeviceSizeCategory
import com.korealm.aria.state.LocalDeviceSizeCategory
import com.korealm.aria.state.PlayerState
import com.korealm.aria.ui.components.player.PlayerMainIcon
import com.korealm.aria.ui.components.player.PlayerSecondaryIcon
import com.korealm.aria.utils.PlayerFacade


@Composable
fun Player(
    playerState: PlayerState,
    playerFacade: PlayerFacade,
    modifier: Modifier = Modifier
) {
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
            PlayerSecondaryIcon(
                iconRes = Res.drawable.volume,
                contentDescription = Res.string.volume_menu,
            ) {}

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

