package com.korealm.aria.ui.components.misc

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.korealm.aria.state.rememberAppThemeState
import com.korealm.aria.utils.darken
import com.korealm.aria.utils.lighten

@Composable
fun GtkButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    val themeState = rememberAppThemeState()

    val containerColor = if (themeState.isDarkTheme) {
        MaterialTheme.colorScheme.surface.lighten(0.7f)
    } else {
        MaterialTheme.colorScheme.surface
    }


    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surface),
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(12.dp))
    ) {
        content()
    }
}