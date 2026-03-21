package com.korealm.aria.ui.components.misc

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.korealm.aria.state.rememberAppThemeState
import com.korealm.aria.utils.GtkButtonShape
import com.korealm.aria.utils.getGtkButtonShape
import com.korealm.aria.utils.getGtkContainerColor

@Composable
fun GtkCard(
    modifier: Modifier = Modifier,
    buttonShape: GtkButtonShape = GtkButtonShape.UNIQUE,
    content: @Composable () -> Unit
) {
    val themeState = rememberAppThemeState()
    val color = getGtkContainerColor(MaterialTheme.colorScheme.surface, themeState.isDarkTheme)
    val shape = getGtkButtonShape(buttonShape)

    Surface(
        color = color,
        contentColor = MaterialTheme.colorScheme.onSurface,
        shape = shape,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surface),
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .shadow(elevation = 3.dp, shape = shape)
    ) {
        content()
    }
}