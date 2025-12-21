package com.korealm.aria.ui.components.misc

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun InvisibleButton(
    title: StringResource,
    subtitle: StringResource? = null,
    modifier: Modifier = Modifier,
    titleModifier: Modifier = Modifier,
    onClick: () -> Unit,
    ripple: Boolean = true,
    children: (@Composable RowScope.() -> Unit)? = null
) {
    Column (
        modifier = Modifier.height(64.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = if (ripple) {
                modifier.clickable { onClick() }
            } else {
                modifier.pointerInput(Unit) {
                    detectTapGestures(onTap = { onClick() })
                }
            }
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
            ) {
                Text(
                    text = stringResource(title),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = titleModifier
                )

                if (subtitle != null) {
                    Text(
                        text = stringResource(subtitle),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        modifier = Modifier
                    )
                }
            }

            Spacer(Modifier.fillMaxWidth().weight(1f))

            children?.invoke(this)
        }
    }
}