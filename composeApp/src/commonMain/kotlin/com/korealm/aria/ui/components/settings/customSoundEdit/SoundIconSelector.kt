package com.korealm.aria.ui.components.settings.customSoundEdit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.audio_edit_icon
import aria.composeapp.generated.resources.audio_edit_name
import aria.composeapp.generated.resources.confirm
import com.korealm.aria.data.CustomSoundIcons
import com.korealm.aria.ui.components.misc.BigIcon
import com.korealm.aria.ui.components.misc.GtkButton
import org.jetbrains.compose.resources.stringResource

@Composable
fun SoundIconSelector(
    modifier: Modifier = Modifier,
    onClick: (CustomSoundIcons) -> Unit
) {
    var newIcon by remember { mutableStateOf<CustomSoundIcons?>(null) }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 24.dp)
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = stringResource(Res.string.audio_edit_icon),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        LazyVerticalGrid (
            columns = GridCells.Fixed(5),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(5.dp)
        ) {
            CustomSoundIcons.entries.forEach { icon ->
                item {
                    BigIcon(
                        iconRes = icon.icon,
                        isActive = icon == newIcon,
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .clickable { newIcon = icon },
                    )
                }
            }
        }

        GtkButton(
            onClick = { newIcon?.let { onClick(it)} },
            enabled = newIcon != null,
            modifier = Modifier
        ) {
            Text(
                text = stringResource(Res.string.confirm),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = if (newIcon != null) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}