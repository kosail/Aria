package com.korealm.aria.ui.components.settings.timer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.timer_custom_duration
import aria.composeapp.generated.resources.timer_quick_presets
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectTime(
    onQuickLaunch: (Long) -> Unit,
    onTimeSet: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val timePickerState = rememberTimePickerState(is24Hour = true)

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(vertical = 24.dp)
    ) {
        Text(
            text = stringResource(Res.string.timer_quick_presets),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            PresetCard(
                amount = 30,
                minutes = true
            ) { onQuickLaunch(30 * 60) }

            PresetCard(
                amount = 1,
            ) { onQuickLaunch(60 * 60) }

            PresetCard(
                amount = 2,
            ) { onQuickLaunch(120 * 60) }

            PresetCard(
                amount = 4,
            ) { onQuickLaunch(240 * 60) }
        }

        Text(
            text = stringResource(Res.string.timer_custom_duration),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(bottom = 16.dp, start = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.6f))
                .border(
                    width = (1.5).dp,
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = RoundedCornerShape(12.dp))
        ) {
            TimeInput(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    timeSelectorSelectedContainerColor = Color.Transparent,
                    timeSelectorUnselectedContainerColor = Color.Transparent,
                    periodSelectorBorderColor = Color.Transparent,
                ),
                modifier = Modifier.padding(top = 16.dp)

            )
        }
    }

    LaunchedEffect(timePickerState.hour, timePickerState.minute) {
        if (timePickerState.hour == 0 && timePickerState.minute == 0) return@LaunchedEffect

        val totalInSeconds = (timePickerState.hour * 3600) + (timePickerState.minute * 60)
        onTimeSet(totalInSeconds.toLong())
    }
}