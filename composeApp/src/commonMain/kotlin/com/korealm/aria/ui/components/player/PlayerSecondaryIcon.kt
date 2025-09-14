package com.korealm.aria.ui.components.player

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.korealm.aria.state.DeviceSizeCategory
import com.korealm.aria.state.LocalDeviceSizeCategory
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PlayerSecondaryIcon(
    iconRes: DrawableResource,
    contentDescription: StringResource,
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val iconSize = when(LocalDeviceSizeCategory.current) {
        DeviceSizeCategory.CompactDesktop -> 36.dp
        else -> 44.dp
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxHeight()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(iconSize)
                .clip(CircleShape)
                .clickable {onClick()}
        ) {
            Icon(
                painter = painterResource(iconRes),
                tint = iconTint,
                contentDescription = stringResource(contentDescription),
                modifier = Modifier.size(iconSize * 0.62f)
            )
        }
    }
}