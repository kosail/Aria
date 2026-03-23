package com.korealm.aria.ui.components.settings.timer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aria.composeapp.generated.resources.*
import com.korealm.aria.ui.components.misc.CustomDialog
import com.korealm.aria.ui.components.misc.GtkButton
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerDialog(
    onDismissRequest: () -> Unit
) {
    CustomDialog(
        onDismissRequest = onDismissRequest,
        showNavbar = true,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    text = stringResource(Res.string.settings_timer_start).uppercase(),
                    style = MaterialTheme.typography.headlineLarge,
                    letterSpacing = 1.sp,
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 24.dp)
            )

            Text(
                text = stringResource(Res.string.timer_quick_presets).uppercase(),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                PresetCard(
                    amount = 30,
                    minutes = true
                ) {}

                PresetCard(
                    amount = 1,
                ) {}

                PresetCard(
                    amount = 2,
                    isSelected = true
                ) {}

                PresetCard(
                    amount = 4,
                ) {}
            }

            Text(
                text = stringResource(Res.string.timer_custom_duration).uppercase(),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.7f))
                    .border(
                        width = (1.5).dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f),
                        shape = RoundedCornerShape(20.dp))
            ) {
                TimeInput(
                    state = rememberTimePickerState(1, 0, true),
                    colors = TimePickerDefaults.colors(
                        timeSelectorSelectedContainerColor = Color.Transparent,
                        timeSelectorUnselectedContainerColor = Color.Transparent
                    ),
                    modifier = Modifier.padding(top = 16.dp)

                )
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )

            GtkButton(
                onClick = {},
                modifier = Modifier.align(Alignment.End),
            ) {
                Text(
                    text = stringResource(Res.string.timer_start),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}