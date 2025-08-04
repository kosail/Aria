package com.korealm.aria.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.unit.dp

@Composable
fun VolumeBar(
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.8f),
    progressColor: Color = MaterialTheme.colorScheme.primary,
    modifier: Modifier = Modifier,
) {
    var thumbOffset by remember { mutableStateOf(0.5f) }

    BoxWithConstraints (
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(8.dp)
    ) {
        val barWidthPx = constraints.maxWidth

        Canvas( // Progress bar
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while(true) {
                            val down = awaitPointerEvent().changes.firstOrNull() ?: continue

                            if (down.pressed) {
                                val initialX = down.position.x
                                var isDrag = false

                                while (down.pressed) {
                                    val event = awaitPointerEvent()
                                    val drag = event.changes.first()

                                    // Detect drag amount
                                    if (drag.positionChange() != Offset.Zero) {
                                        isDrag = true
                                        val dragAmount = drag.positionChange().x
                                        val thumbPx = barWidthPx * thumbOffset + dragAmount
                                        thumbOffset = (thumbPx / barWidthPx).coerceIn(0f, 1f)
                                        drag.consume()
                                    } else {
                                        break
                                    }
                                }

                                // If it was a tap, set position based on tap location
                                if (!isDrag) {
                                    thumbOffset = (initialX / barWidthPx).coerceIn(0f, 1f)
                                }
                            }
                        }
                    }
                }
        ) {
            drawRect( // Background
                color = backgroundColor,
                size = size
            )

            drawRect( // Volume bar
                color = progressColor,
                size = Size(size.width * thumbOffset, size.height),
            )
        }
    }
}