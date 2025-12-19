package com.korealm.aria.ui.components.volume

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.general_volume
import aria.composeapp.generated.resources.reset_sounds
import aria.composeapp.generated.resources.volume_off
import com.korealm.aria.state.AppThemeState
import com.korealm.aria.ui.components.CustomDropdownMenu
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun VolumeMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onResetButton: () -> Unit,
    volume: Float,
    offset: DpOffset,
    onVolumeChange: (Float) -> Unit,
    themeState: AppThemeState,
    modifier: Modifier = Modifier
) {
    CustomDropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        offset = offset,
        themeState = themeState,
        modifier = modifier
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = stringResource(Res.string.general_volume),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            VolumeBar(
                actualVolume = volume,
                isSelected = true, // Audios require this to swap between active/disabled colors. So let's just mock that it's "active" to display colors.
                themeState = themeState,
                onVolumeChange = onVolumeChange,
            )
        }

        HorizontalDivider(modifier = Modifier.padding(horizontal = 1.dp, vertical = 4.dp))

        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(Res.string.reset_sounds),
                    style = MaterialTheme.typography.bodyLarge,
                )
            },
            onClick = onResetButton,
            leadingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.volume_off),
                    contentDescription = stringResource(Res.string.reset_sounds),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        )
    }
}