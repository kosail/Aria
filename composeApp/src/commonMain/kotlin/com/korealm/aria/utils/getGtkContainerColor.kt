package com.korealm.aria.utils

import androidx.compose.ui.graphics.Color

fun getGtkContainerColor(color: Color, isDarkTheme: Boolean) = if (isDarkTheme) {
    color.lighten(0.7f)
} else {
    color
}