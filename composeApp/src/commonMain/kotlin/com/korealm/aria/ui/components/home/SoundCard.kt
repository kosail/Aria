package com.korealm.aria.ui.components.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TooltipDefaults.rememberTooltipPositionProvider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.korealm.aria.model.Sound
import com.korealm.aria.shared.Target
import com.korealm.aria.shared.getTargetPlatform
import com.korealm.aria.state.AppThemeState
import com.korealm.aria.ui.components.misc.BigIcon
import com.korealm.aria.ui.components.misc.StyledTooltip
import com.korealm.aria.ui.components.volume.VolumeBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoundCard(
    sound: Sound,
    cardSize: Dp,
    modifier: Modifier = Modifier,
    themeState: AppThemeState,
    onVolumeChange: (Double) -> Unit,
    onClick: () -> Unit
) {
    val bottomBarPadding = when(getTargetPlatform()) {
        Target.DESKTOP -> 22.dp
        else -> 24.dp
    }

    Box(
        modifier = modifier.size(cardSize)
    ) {
        HomeScreenCard(themeState, onClick) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                val title = sound.resource.title

                TooltipBox(
                    tooltip = { StyledTooltip(title) },
                    state = rememberTooltipState(),
                    positionProvider = rememberTooltipPositionProvider(
                        positioning = TooltipAnchorPosition.Below,
                        spacingBetweenTooltipAndAnchor = 4.dp
                    )
                ) {
                    BigIcon(
                        iconRes = sound.resource.icon,
                        contentDescription = title,
                        isActive = sound.isSelected,
                        iconColor = if (sound.isSelected) {
                            MaterialTheme.colorScheme.onSurface
                        } else {
                            Color.Gray
                        },
                    )
                }

                Spacer(Modifier.height(36.dp))
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = bottomBarPadding)
        ) {
            VolumeBar(
                actualVolume = sound.volume,
                isSelected = sound.isSelected,
                themeState = themeState,
                modifier = Modifier.padding(horizontal = 18.dp)
            ) { newVolume -> onVolumeChange(newVolume) }
        }
    }
}