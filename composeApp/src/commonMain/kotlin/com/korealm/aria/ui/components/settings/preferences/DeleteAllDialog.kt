package com.korealm.aria.ui.components.settings.preferences

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
import aria.composeapp.generated.resources.*
import com.korealm.aria.ui.components.misc.GtkButton
import com.korealm.aria.ui.components.misc.SimpleNavbar
import org.jetbrains.compose.resources.stringResource

@Composable
fun DeleteAllDialog(
    onDismissRequest: () -> Unit,
    onDeleteAll: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        SimpleNavbar(
            title = stringResource(Res.string.delete_all_personal_sounds),
            onBack = onDismissRequest,
        )

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Text(
                    text = stringResource(Res.string.delete_all_personal_sounds_confirm),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )

                Text(
                    text = stringResource(Res.string.audio_delete_confirm_caption_all),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.weight(1f))

                GtkButton(
                    onClick = onDeleteAll,
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
}