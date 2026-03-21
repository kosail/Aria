package com.korealm.aria.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

enum class GtkButtonShape {
    UNIQUE, TOP, MIDDLE, BOTTOM
}

fun getGtkButtonShape(buttonShape: GtkButtonShape = GtkButtonShape.UNIQUE) = when (buttonShape) {
    GtkButtonShape.TOP -> RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp)
    GtkButtonShape.MIDDLE -> RoundedCornerShape(0.dp)
    GtkButtonShape.BOTTOM -> RoundedCornerShape(0.dp, 0.dp, 12.dp, 12.dp)
    else -> RoundedCornerShape(12.dp)
}