package com.korealm.aria.ui.components.misc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.from_honduras
import aria.composeapp.generated.resources.kosail_in_korealm
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Clock

@Composable
fun Copyright(
    modifier: Modifier = Modifier
) {
    val year = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "2025 - $year © ${stringResource(Res.string.kosail_in_korealm)}",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )

        Text(
            text = stringResource(Res.string.from_honduras),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
        )
    }
}