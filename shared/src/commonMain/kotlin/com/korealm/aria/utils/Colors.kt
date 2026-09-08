package com.korealm.aria.utils

import androidx.compose.ui.graphics.Color

fun Color.lighten(factor: Float = 0.1f): Color {
    return copy(
        alpha = alpha,
        red = (red * (1 + factor)).coerceIn(0f, 1f),
        green = (green * (1 + factor)).coerceIn(0f, 1f),
        blue = (blue * (1 + factor)).coerceIn(0f, 1f),
    )}

fun Color.darken(factor: Float = 0.1f): Color {
    return copy(
        alpha = alpha,
        red = (red * (1 - factor)).coerceIn(0f, 1f),
        green = (green * (1 - factor)).coerceIn(0f, 1f),
        blue = (blue * (1 - factor)).coerceIn(0f, 1f),
    )
}