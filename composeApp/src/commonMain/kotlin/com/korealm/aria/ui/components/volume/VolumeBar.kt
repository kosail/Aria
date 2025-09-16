package com.korealm.aria.ui.components.volume

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp

@Composable
fun VolumeBar(
    value: Float,
    backgroundColor: Color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
    progressColor: Color = MaterialTheme.colorScheme.primary,
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit
) {
    var localValue by remember { mutableStateOf(value) }

    var barWidth by remember { mutableStateOf(1) }

    val dragState = rememberDraggableState { delta ->
        localValue = (localValue + delta / barWidth).coerceIn(0f, 1f)
        onValueChange(localValue)
    }

    Box (
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(8.dp)
    ) {
        Canvas( // Progress bar
            modifier = Modifier
                .fillMaxSize()
                .onSizeChanged { barWidth = it.width }
                .clip(RoundedCornerShape(5.dp))
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = dragState
                )
        ) {
            drawRect( // Background
                color = backgroundColor,
                size = size
            )

            drawRect( // Volume bar
                color = progressColor,
                size = Size(size.width * localValue, size.height),
            )
        }
    }
}