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
import com.korealm.aria.ui.components.misc.BigIcon
import com.korealm.aria.ui.components.volume.VolumeBar
import com.korealm.aria.utils.Target
import com.korealm.aria.utils.getTargetPlatform
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
        if (themeState.isDarkTheme) 0.1f else 1f
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

    val bottomBarPadding = when(getTargetPlatform()) {
        Target.DESKTOP -> 22.dp
        else -> 24.dp
    }

    Box(
        modifier = modifier.size(cardSize)
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
                .padding(bottom = bottomBarPadding)
        ) {
            VolumeBar(
                actualVolume = sound.volume,
                isSelected = sound.isSelected,
                themeState = themeState,
                modifier = Modifier.padding(horizontal = 18.dp)
            ) { newVolume -> onVolumeChange(newVolume) }
        }
    }
}