package com.korealm.aria.ui.components

import androidx.compose.animation.animateColorAsState
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
import org.jetbrains.compose.resources.stringResource

@Composable
fun SoundCard(
    sound: Sound,
    cardSize: Dp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var isHover by remember { mutableStateOf(false) }

    val surfaceColor = if (isHover) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f) else Color.Transparent
    val animatedColor by animateColorAsState( targetValue = surfaceColor )

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
                    isActive = sound.isSelected
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
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}