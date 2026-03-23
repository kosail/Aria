package com.korealm.aria.ui.components.misc

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.korealm.aria.state.LocalThemeState
import com.korealm.aria.utils.GtkButtonShape
import com.korealm.aria.utils.getGtkButtonShape
import com.korealm.aria.utils.getGtkContainerColor


@Composable
fun GtkButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    buttonShape: GtkButtonShape = GtkButtonShape.UNIQUE,
    content: @Composable RowScope.() -> Unit
) {
    val themeState = LocalThemeState.current

    val color = getGtkContainerColor(MaterialTheme.colorScheme.surface, themeState.isDarkTheme)
    val shape = getGtkButtonShape(buttonShape)

    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = shape,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surface),
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .shadow(elevation = 3.dp, shape = shape)
    ) {
        content()
    }
}