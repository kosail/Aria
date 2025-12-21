package com.korealm.aria.ui.components.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aria.composeapp.generated.resources.*
import com.korealm.aria.shared.Target
import com.korealm.aria.shared.getTargetPlatform
import com.korealm.aria.ui.components.misc.CustomDialog
import com.korealm.aria.ui.components.misc.InvisibleButton
import org.jetbrains.compose.resources.stringResource

@Composable
fun PreferencesDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier
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
                    .padding(vertical = 8.dp)
            )

            Column (
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    text = stringResource(Res.string.theme_accent_color),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

//                MOCK COLOR SELECTOR FOR NOW. TODO: IMPLEMENT ACTUAL SELECTOR
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
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFC34043))
                        )

                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF76946A))
                        )

                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFC0A36E))
                        )

                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF7E9CD8))
                        )

                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF957FB8))
                        )

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                        ){
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary)
                            )
                        }
                    }
                }

                if (getTargetPlatform() != Target.ANDROID) {
                    var inhibitSleep by remember { mutableStateOf(false) }

                    InvisibleButton(
                        title = Res.string.inhibit_suspension_title,
                        subtitle = Res.string.inhibit_suspension,
                        ripple = false,
                        onClick = { inhibitSleep = !inhibitSleep },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Switch(
                            checked = inhibitSleep,
                            onCheckedChange = { inhibitSleep = !inhibitSleep },
                            colors = SwitchDefaults.colors(
                                uncheckedTrackColor = MaterialTheme.colorScheme.surface,
                            ),
                            modifier = modifier.scale(0.9f)
                        )
                    }
                }

                InvisibleButton(
                    title = Res.string.delete_all_personal_sounds,
                    onClick = { },
                    modifier = Modifier.clip(RoundedCornerShape(16.dp)),
                    titleModifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp)
                )
            }

            Spacer(Modifier.fillMaxHeight().weight(1f))

            Text(
                text = stringResource(Res.string.suggestions),
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
}