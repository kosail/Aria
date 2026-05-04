package com.korealm.aria.ui.components.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.add_symbolic
import aria.composeapp.generated.resources.audio_add
import com.korealm.aria.state.AppThemeState
import com.korealm.aria.ui.components.misc.BigIcon
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddSoundCard(
    themeState: AppThemeState,
    cardSize: Dp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier.size(cardSize)
    ) {
        HomeScreenCard(
            themeState = themeState,
            onLongClick = null,
            onClick = onClick) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxSize()
            ) {

                BigIcon(
                    iconRes = Res.drawable.add_symbolic,
                    contentDescription = null,
                    isActive = false,
                    iconColor = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(Modifier.height(36.dp))
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp)
            ) {
                Text(
                    text = stringResource(Res.string.audio_add),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}