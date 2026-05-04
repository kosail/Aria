package com.korealm.aria.ui.components.settings.iconPicker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.settings_preferences
import com.korealm.aria.model.AudioResource
import com.korealm.aria.ui.components.misc.CustomDialog
import org.jetbrains.compose.resources.stringResource

@Composable
fun IconPickerDialog(
    audio: AudioResource,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    CustomDialog(
        onDismissRequest = onDismissRequest,
        showNavbar = true,
        modifier = modifier
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
                    text = sanitizeTitle(audio.title).uppercase(),
                    style = MaterialTheme.typography.headlineLarge,
                    letterSpacing = 1.sp,
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }
    }
}

private fun sanitizeTitle(title: String): String {
    return if (title.length < 25) title else title.substring(0, 25) + "..."
}