package com.korealm.aria.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.korealm.aria.state.DeviceSizeCategory
import com.korealm.aria.state.LocalDeviceSizeCategory
import com.korealm.aria.utils.Target
import com.korealm.aria.utils.getTargetPlatform
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun BigIcon(
    iconRes: DrawableResource,
    contentDescription: String? = null,
    isActive: Boolean = false,
    iconColor: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
) {
    val color = if (isActive) MaterialTheme.colorScheme.tertiary else Color.Transparent

    val animatedColor by animateColorAsState(
        targetValue = color,
        animationSpec =tween(130, easing = LinearEasing)
    )

    val iconSize = when(LocalDeviceSizeCategory.current) {
        DeviceSizeCategory.Mobile -> 64.dp
        else -> if(getTargetPlatform() == Target.WEB) 96.dp else 76.dp
    }

    val circleSize = when (LocalDeviceSizeCategory.current) {
        DeviceSizeCategory.Mobile -> if(getTargetPlatform() == Target.WEB) 72.dp else 80.dp
        else -> if(getTargetPlatform() == Target.WEB) 150.dp else 110.dp
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(circleSize)
            .clip(CircleShape)
            .background(animatedColor)
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = contentDescription,
            tint = iconColor,
            modifier = modifier.size(iconSize),
        )
    }
}