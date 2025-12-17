package com.korealm.aria.ui.components.volume

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.korealm.aria.state.AppThemeState
import com.korealm.aria.utils.Target
import com.korealm.aria.utils.getTargetPlatform

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VolumeBar(
    actualVolume: Float,
    isSelected: Boolean,
    themeState: AppThemeState,
    modifier: Modifier = Modifier,
    onVolumeChange: (Float) -> Unit
) {
    val barHeight = when(getTargetPlatform()) {
        Target.DESKTOP -> 8.dp
        else -> 10.dp
    }

    Slider(
        value = actualVolume,
        onValueChange = onVolumeChange,
        valueRange = 0f..1f,
        modifier = modifier.height(1.dp),
        thumb = {},
        track = { sliderState ->
            val fraction by remember {
                derivedStateOf {
                    (sliderState.value - sliderState.valueRange.start) / (sliderState.valueRange.endInclusive - sliderState.valueRange.start)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
            ) {
                Box(
                    Modifier
                        .fillMaxWidth(fraction)
                        .align(Alignment.CenterStart)
                        .height(barHeight)
                        .background(
                            if (isSelected) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                if (themeState.isDarkTheme) Color.DarkGray else Color.LightGray
                            }
                        )
                )
                Box(
                    Modifier
                        .fillMaxWidth(1f - fraction)
                        .align(Alignment.CenterEnd)
                        .height(barHeight)
                        .background(
                            if (isSelected) {
                                if (themeState.isDarkTheme) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f) else Color.LightGray.copy(alpha = 0.9f)
                            } else {
                                if (themeState.isDarkTheme) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f) else Color.LightGray.copy(alpha = 0.6f)
                            }
                        )
                )
            }
        }
    )
}