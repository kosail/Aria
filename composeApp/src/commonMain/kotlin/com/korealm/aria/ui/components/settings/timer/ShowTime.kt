package com.korealm.aria.ui.components.settings.timer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.aria.state.DeviceSizeCategory.*
import com.korealm.aria.state.LocalDeviceSizeCategory


// Not Ado's Showtime, but literally show time. You get it? Ado-rable.

@Composable
fun ShowTime(
    timeInSeconds: Long,
    modifier: Modifier = Modifier
) {
    val hours = "${timeInSeconds / 3600}".padStart(2, '0')
    val minutes = "${(timeInSeconds % 3600) / 60}".padStart(2, '0')
    val seconds = "${timeInSeconds % 60}".padStart(2, '0')

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(vertical = 24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            SimpleText(text = hours)
            SimpleText(
                text = ":",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            SimpleText(text = minutes)
            SimpleText(
                text = ":",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            SimpleText(text = seconds)
        }
    }
}

@Composable
private fun SimpleText(
    text: String,
    color: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
) {
    val device = LocalDeviceSizeCategory.current
    val textSize = when (device) {
        Mobile -> 70.sp
        CompactDesktop -> 70.sp
        FullDesktop -> 80.sp
    }

    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontSize = textSize,
        fontWeight = FontWeight.Normal,
        color = color,
        modifier = modifier
    )
}