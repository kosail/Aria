package com.korealm.aria.ui.components.settings.customSoundEdit

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.korealm.aria.model.AudioResource
import com.korealm.aria.state.LocalPlayerState
import com.korealm.aria.ui.components.misc.CustomDialog
import com.korealm.aria.utils.LocalPlayerFacadeState
import kotlinx.coroutines.launch

@Composable
fun CustomSoundEditDialog(
    audio: AudioResource,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    val playerState = LocalPlayerState.current
    val playerFacade = LocalPlayerFacadeState.current

    val scope = rememberCoroutineScope()

    var page by remember { mutableStateOf(CustomSoundPages.HOME) }

    val height = when (page) {
        CustomSoundPages.HOME -> 310.dp
        CustomSoundPages.EDIT_NAME -> 250.dp
        CustomSoundPages.EDIT_ICON -> 600.dp
        CustomSoundPages.DELETE -> 300.dp
    }
    val animatedHeight by animateDpAsState(height)

    CustomDialog(
        onDismissRequest = onDismissRequest,
        showNavbar = true,
        height = animatedHeight
    ) {
        AnimatedContent(
            targetState = page,
            transitionSpec = {
                slideInHorizontally(animationSpec =
                    tween(500)) { fullWidth -> fullWidth } + fadeIn(animationSpec = tween(500)
                ) togetherWith

                        slideOutHorizontally(animationSpec =
                            tween(500)) { fullWidth -> -fullWidth } + fadeOut(animationSpec = tween(500)
                )
            }
        ) { navItem ->
            when (navItem) {
                CustomSoundPages.HOME -> {
                    SoundHomeActions(onTabChange = { page = it }, title = audio.title)
                }
                CustomSoundPages.EDIT_NAME -> SoundNameUpdater { name ->
                    scope.launch {
                        playerState.updateTitle(audio.id, name)
                        onDismissRequest()
                    }
                }
                CustomSoundPages.EDIT_ICON -> SoundIconSelector { icon ->
                    scope.launch {
                        playerState.updateIcon(audio.id, icon)
                        onDismissRequest()
                    }
                }
                CustomSoundPages.DELETE -> {
                    val currentSound = playerState.playlist.find { it.resource.id == audio.id }
                    val title = currentSound?.resource?.title ?: audio.title
                    SoundDelete(title = title) {
                        scope.launch {
                            if (currentSound?.isPlaying == true) playerFacade.stop(currentSound)
                            playerState.deleteUserSound(audio.id)
                            onDismissRequest()
                        }
                    }
                }
            }
        }
    }
}