package com.korealm.aria.ui.components.settings.about

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.korealm.aria.ui.components.misc.CustomDialog


@Composable
fun AboutDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    var page by remember { mutableStateOf(AboutPages.HOME) }

    CustomDialog(
        onDismissRequest = onDismissRequest,
        showNavbar = true,
        modifier = modifier
    ) {
        val backToHome: () -> Unit = { page = AboutPages.HOME }

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
                AboutPages.HOME -> AboutHome(onTabChange = { page = it })
                AboutPages.DONATE -> AboutDonate(onBack = backToHome)
                AboutPages.THIS_APP -> AboutThisApp(onBack = backToHome)
                AboutPages.LEGAL -> AboutLegal(onBack = backToHome)
                AboutPages.CREDITS -> AboutCredits(onBack = backToHome)
            }
        }
    }
}