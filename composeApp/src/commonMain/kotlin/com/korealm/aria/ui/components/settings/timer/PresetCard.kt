package com.korealm.aria.ui.components.settings.timer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.timer_hours
import aria.composeapp.generated.resources.timer_minutes_short
import com.korealm.aria.shared.Target
import com.korealm.aria.shared.getTargetPlatform
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

    val cardSize: Triple<Dp, Dp, Dp> = if (getTargetPlatform() != Target.ANDROID) Triple(86.dp, 80.dp, 74.dp) else Triple(74.dp, 68.dp, 62.dp)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(cardSize.first)
            .clip(RoundedCornerShape(32.dp))
            .background(bgColor)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(cardSize.second)
                .clip(RoundedCornerShape(28.dp))
                .background(MaterialTheme.colorScheme.background)
        ) {
            Surface(
                color = surfaceColor,
                shape = RoundedCornerShape(26.dp),
                modifier = Modifier.size(cardSize.third)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .size(cardSize.third)
                        .clickable { onClick() }
                ) {
                    Text(
                        text = amount.toString(),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.background,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                    )

                    Text(
                        text = stringResource(if (minutes) Res.string.timer_minutes_short else Res.string.timer_hours),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}