package com.korealm.aria.ui.components.settings.preferences

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aria.composeapp.generated.resources.*
import com.korealm.aria.model.ThemeMode
import com.korealm.aria.shared.Target.ANDROID
import com.korealm.aria.shared.getTargetPlatform
import com.korealm.aria.state.LocalPlayerState
import com.korealm.aria.state.LocalSettings
import com.korealm.aria.state.LocalThemeState
import com.korealm.aria.theme.AccentColor
import com.korealm.aria.ui.components.misc.CustomDialog
import com.korealm.aria.ui.components.misc.GtkButton
import com.korealm.aria.utils.LocalPlayerFacadeState
import com.korealm.aria.utils.getColorScheme
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@Composable
fun PreferencesDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    val themeState = LocalThemeState.current
    val settingState = LocalSettings.current
    val currentMode = settingState.currentSettings.themeMode

    var isDeleteAllDialog by remember { mutableStateOf(false) }

    val colorSchemes = AccentColor.entries.associateWith { getColorScheme(it, themeState.isDarkTheme) }

    CustomDialog(
        onDismissRequest = onDismissRequest,
        showNavbar = true,
        modifier = modifier.verticalScroll(rememberScrollState())
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
                    text = stringResource(Res.string.settings_preferences).uppercase(),
                    style = MaterialTheme.typography.headlineLarge,
                    letterSpacing = 1.sp,
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )

            Column (
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Per device target settings section
                // ---------------------------------
                Text(
                    text = stringResource(Res.string.theme_mode),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )


                SingleChoiceSegmentedButtonRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    SegmentedButton(
                        shape = RoundedCornerShape(topStartPercent = 20, bottomStartPercent = 20),
                        onClick = { themeState.setThemeMode(ThemeMode.SYSTEM) },
                        selected = currentMode == ThemeMode.SYSTEM,
                        colors = SegmentedButtonDefaults.colors(
                            activeContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                        ),
                        icon = {},
                        modifier = Modifier.height(48.dp)
                    ) {
                        Text(
                            text = stringResource(Res.string.theme_mode_system).uppercase()
                        )
                    }

                    SegmentedButton(
                        shape = RectangleShape,
                        onClick = { themeState.setThemeMode(ThemeMode.DARK) },
                        selected = currentMode == ThemeMode.DARK,
                        colors = SegmentedButtonDefaults.colors(
                            activeContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                        ),
                        icon = {},
                        modifier = Modifier.height(48.dp)
                    ) {
                        Text(
                            text = stringResource(Res.string.theme_mode_dark).uppercase()
                        )
                    }

                    SegmentedButton(
                        shape = RoundedCornerShape(topEndPercent = 20, bottomEndPercent = 20),
                        onClick = { themeState.setThemeMode(ThemeMode.LIGHT) },
                        selected = currentMode == ThemeMode.LIGHT,
                        colors = SegmentedButtonDefaults.colors(
                            activeContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                        ),
                        icon = {},
                        modifier = Modifier.height(48.dp)
                    ) {
                        Text(
                            text = stringResource(Res.string.theme_mode_light).uppercase()
                        )
                    }
                }

                Text(
                    text = stringResource(Res.string.theme_accent_color),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Surface(
                    color = Color.Transparent,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(bottom = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        colorSchemes.forEach { (accentColor, scheme) ->
                            val isSelected = settingState.currentSettings.accentColor == accentColor
                            val primaryColor = Color(scheme.primary)

                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .clickable {
                                        settingState.update { copy(accentColor = accentColor) }
                                    }
                                    .background(
                                        if (isSelected) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f) else Color.Transparent
                                    )
                            ){
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(primaryColor)
                                )
                            }
                        }

                    }
                }

                if (getTargetPlatform() == ANDROID) {
                    GtkButton(
                        onClick = { isDeleteAllDialog = true },
                        modifier = Modifier.clip(RoundedCornerShape(16.dp)),
                    ) {
                        Text(
                            text = stringResource(Res.string.delete_all_personal_sounds),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            Spacer(Modifier.fillMaxHeight().weight(1f))

            Text(
                text = stringResource(Res.string.suggestions).trimIndent(),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }
    }

    val playerState = LocalPlayerState.current
    val playerFacade = LocalPlayerFacadeState.current
    val scope = rememberCoroutineScope()

    AnimatedVisibility(isDeleteAllDialog) {
        DeleteAllDialog(
            onDismissRequest = { isDeleteAllDialog = false },
            onDeleteAll = {
                scope.launch {
                    playerFacade.stopAllCustomSounds()
                    playerState.deleteAllUserSounds()
                    isDeleteAllDialog = false
                    onDismissRequest()
                }
            }
        )
    }
}