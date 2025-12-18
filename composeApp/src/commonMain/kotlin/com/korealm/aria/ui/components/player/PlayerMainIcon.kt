package com.korealm.aria.ui.components.player

import androidx.compose.foundation.background
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
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.pause
import aria.composeapp.generated.resources.play
import com.korealm.aria.utils.Target.*
import com.korealm.aria.utils.getTargetPlatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PlayerMainIcon(
    backgroundColor: Color = MaterialTheme.colorScheme.tertiary,
    iconTint: Color,
    isPlaying: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val iconSize = when (getTargetPlatform()) {
        WEB -> 60.dp
        DESKTOP -> 54.dp
        ANDROID -> 60.dp
    }

    // Not using an IconButton because I did not like the default behavior on hover,
    // so I preferred a simpler solution.
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxHeight()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(iconSize)
                .clip(CircleShape)
                .background(backgroundColor)
                .clickable {onClick()}
        ) {
            Icon(
                painter = painterResource(
                    if (isPlaying) Res.drawable.pause else Res.drawable.play
                ),
                tint = iconTint,
                contentDescription = stringResource(if (isPlaying) Res.string.pause else Res.string.play),
                modifier = Modifier.size(iconSize/2)
            )
        }
    }
}