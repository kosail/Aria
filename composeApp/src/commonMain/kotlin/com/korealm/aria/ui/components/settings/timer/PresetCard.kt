package com.korealm.aria.ui.components.settings.timer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.timer_hours
import aria.composeapp.generated.resources.timer_minutes_short
import org.jetbrains.compose.resources.stringResource

@Composable
fun PresetCard(
    amount: Int,
    minutes: Boolean = false,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val bgColor = if (isSelected) MaterialTheme.colorScheme.tertiary else Color.Transparent
    val surfaceColor = if (isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.tertiaryContainer

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(92.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(bgColor)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(86.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(MaterialTheme.colorScheme.background)
        ) {
            Surface(
                color = surfaceColor,
                shape = RoundedCornerShape(26.dp),
                modifier = Modifier.size(80.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { onClick() }
                ) {
                    Text(
                        text = amount.toString(),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier
                    )

                    Text(
                        text = stringResource(if (minutes) Res.string.timer_minutes_short else Res.string.timer_hours),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}