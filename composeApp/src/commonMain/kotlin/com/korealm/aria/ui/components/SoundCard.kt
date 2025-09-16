package com.korealm.aria.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.korealm.aria.model.Sound
import com.korealm.aria.state.AppThemeState
import com.korealm.aria.ui.components.volume.VolumeBar
import org.jetbrains.compose.resources.stringResource

@Composable
fun SoundCard(
    sound: Sound,
    cardSize: Dp,
    modifier: Modifier = Modifier,
    themeState: AppThemeState,
    onVolumeChange: (Float) -> Unit,
    onClick: () -> Unit
) {
    var isHover by remember { mutableStateOf(false) }

    val baseColor = if (themeState.isDarkTheme) {
        MaterialTheme.colorScheme.tertiary
    } else {
        MaterialTheme.colorScheme.tertiaryContainer
    }

    val targetAlpha = if (isHover) {
        if (themeState.isDarkTheme) 0.15f else 1f
    } else {
        0f
    }

    val animatedAlpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(100)
    )

    val animatedColor = baseColor.copy(alpha = animatedAlpha)

    val iconRes = sound.resource.iconRes
    val titleRes = sound.resource.titleRes

    Box(
        modifier = modifier
            .size(cardSize)
    ) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = animatedColor,
            modifier = modifier
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while(true) {
                            val type = awaitPointerEvent().type

                            when (type) {
                                PointerEventType.Enter -> isHover = true
                                PointerEventType.Exit -> isHover = false
                            }
                        }
                    }
                }
                .clickable { onClick() }
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                BigIcon(
                    iconRes = iconRes,
                    contentDescription = stringResource(titleRes),
                    isActive = sound.isSelected,
                    iconColor = if (sound.isSelected) {
                        MaterialTheme.colorScheme.onSurface
                    } else {
                        Color.Gray
                    },
                )

                Spacer(Modifier.height(36.dp))
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        ) {
            VolumeBar(
                value = sound.volume,
                backgroundColor = if (sound.isSelected) {
                    if (themeState.isDarkTheme) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f) else Color.LightGray.copy(alpha = 0.9f)
                } else {
                    if (themeState.isDarkTheme) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f) else Color.LightGray.copy(alpha = 0.6f)
                },
                progressColor = if (sound.isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    if (themeState.isDarkTheme) Color.DarkGray else Color.LightGray
                },
                modifier = Modifier.padding(horizontal = 24.dp)
            ) { newVolume ->
                onVolumeChange(newVolume)
            }
        }
    }
}