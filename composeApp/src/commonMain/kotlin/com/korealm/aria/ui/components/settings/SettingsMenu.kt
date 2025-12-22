package com.korealm.aria.ui.components.settings

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import aria.composeapp.generated.resources.*
import com.korealm.aria.state.AppThemeState
import com.korealm.aria.ui.components.misc.CustomDropdownMenu
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onTimerButton: () -> Unit,
    onPreferencesButton: () -> Unit,
    onAboutButton: () -> Unit,
    offset: DpOffset,
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
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(Res.string.settings_timer_start),
                    style = MaterialTheme.typography.bodyLarge,
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.timer),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            },
            onClick = onTimerButton,
        )

        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(Res.string.settings_preferences),
                    style = MaterialTheme.typography.bodyLarge,
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.settings),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            },
            onClick = onPreferencesButton,
        )

        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(Res.string.settings_about),
                    style = MaterialTheme.typography.bodyLarge,
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.info),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            },
            onClick = onAboutButton,
        )
    }
}