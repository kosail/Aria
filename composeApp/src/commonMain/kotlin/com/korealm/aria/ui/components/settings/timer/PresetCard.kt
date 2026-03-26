package com.korealm.aria.ui.components.settings.timer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val cardSize = if (getTargetPlatform() != Target.ANDROID) 74.dp else 68.dp

    Surface(
        color = MaterialTheme.colorScheme.tertiary,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.size(cardSize)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .size(cardSize)
                .clickable { onClick() }
        ) {
            Text(
                text = amount.toString(),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight.Medium,
            )

            Text(
                text = stringResource(if (minutes) Res.string.timer_minutes_short else Res.string.timer_hours),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.background,
            )
        }
    }
}