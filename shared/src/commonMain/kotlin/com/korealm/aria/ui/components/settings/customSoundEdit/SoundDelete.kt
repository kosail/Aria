package com.korealm.aria.ui.components.settings.customSoundEdit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aria.shared.generated.resources.*
import com.korealm.aria.ui.components.misc.GtkButton
import org.jetbrains.compose.resources.stringResource

@Composable
fun SoundDelete(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Text(
            text = stringResource(Res.string.audio_delete_specific, title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().height(56.dp)
        )

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Text(
                text = stringResource(Res.string.audio_delete_confirm),
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = stringResource(Res.string.audio_delete_confirm_caption),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Light,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.weight(1f))

            GtkButton(
                onClick = onClick,
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                Text(
                    text = stringResource(Res.string.confirm_yes),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}