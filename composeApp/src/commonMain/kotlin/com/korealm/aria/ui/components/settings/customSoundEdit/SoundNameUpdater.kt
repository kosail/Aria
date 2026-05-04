package com.korealm.aria.ui.components.settings.customSoundEdit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.audio_edit_name
import aria.composeapp.generated.resources.audio_edit_new_name
import aria.composeapp.generated.resources.audio_edit_new_name_limit
import aria.composeapp.generated.resources.confirm
import com.korealm.aria.ui.components.misc.GtkButton
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SoundNameUpdater(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    var newName by remember { mutableStateOf("") }

    LaunchedEffect(newName) {
        delay(50.milliseconds)
        newName = newName.take(22)
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 24.dp)
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = stringResource(Res.string.audio_edit_name),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        Column(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .padding(horizontal = 8.dp)
        ) {
            TextField(
                placeholder = {
                    Text(
                        text = stringResource(Res.string.audio_edit_new_name),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                },
                value = newName,
                onValueChange = { newName = it },
                singleLine = true,
                shape = RoundedCornerShape(4.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f),
                    unfocusedContainerColor = Color.Gray.copy(alpha = 0.2f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(Res.string.audio_edit_new_name_limit) + "${newName.length}/22",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )
        }

        GtkButton(
            onClick = { onClick(newName) },
            enabled = newName.isNotEmpty(),
            modifier = Modifier
        ) {
            Text(
                text = stringResource(Res.string.confirm),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = if (newName.isNotEmpty()) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}