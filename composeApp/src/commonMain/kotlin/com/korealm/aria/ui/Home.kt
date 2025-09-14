package com.korealm.aria.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aria.composeapp.generated.resources.AlegreyaSansSC_Bold
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.aria
import com.korealm.aria.state.DeviceSizeCategory
import com.korealm.aria.state.LocalDeviceSizeCategory
import com.korealm.aria.state.PlayerState
import com.korealm.aria.ui.components.SoundCard
import com.korealm.aria.utils.PlayerFacade
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource

@Composable
fun Home(
    playerState: PlayerState,
    playerFacade: PlayerFacade,
    modifier: Modifier = Modifier
) {
    val mainSurfacePadding = when (LocalDeviceSizeCategory.current) {
        DeviceSizeCategory.Mobile -> 20.dp
        DeviceSizeCategory.CompactDesktop -> 60.dp
        DeviceSizeCategory.FullDesktop -> 60.dp
    }

    Box(
        modifier = modifier
            .padding(horizontal = mainSurfacePadding)
            .padding(top = mainSurfacePadding, bottom = 5.dp)
    ) {

        val titleFont = FontFamily(
            Font(Res.font.AlegreyaSansSC_Bold, weight = FontWeight.Bold)
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                contentAlignment = if (LocalDeviceSizeCategory.current == DeviceSizeCategory.Mobile) Alignment.Center else Alignment.CenterStart,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(Res.string.aria),
                    fontSize = 72.sp,
                    fontFamily = titleFont,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            val soundCardPadding = when (LocalDeviceSizeCategory.current) {
                DeviceSizeCategory.Mobile -> 12.dp
                DeviceSizeCategory.CompactDesktop -> 18.dp
                DeviceSizeCategory.FullDesktop -> 24.dp
            }


            val soundCardWidth = when (LocalDeviceSizeCategory.current) {
                DeviceSizeCategory.Mobile -> 124.dp
                else -> 216.dp
            }

            LazyVerticalGrid (
                columns = GridCells.Adaptive(minSize = soundCardWidth),
                horizontalArrangement = Arrangement.spacedBy(soundCardPadding / 2),
                verticalArrangement = Arrangement.spacedBy(soundCardPadding / 2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
            ) {
                playerState.playlist.forEach { sound ->
                    item {
                        SoundCard(sound = sound, cardSize = soundCardWidth) {
                            val updated = sound.copy(isSelected = !sound.isSelected)
                            val index = playerState.playlist.indexOf(sound)

                            if (index != -1) playerState.playlist[index] = updated

                            if (updated.isSelected) playerFacade.play(updated) else playerFacade.stop(updated)
                        }
                    }
                }
            }


        }
    }
}