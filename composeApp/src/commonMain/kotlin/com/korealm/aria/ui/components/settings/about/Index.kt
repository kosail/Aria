package com.korealm.aria.ui.components.settings.about

import androidx.compose.animation.AnimatedContent
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
        AnimatedContent(
            targetState = page,
        ) { navItem ->
            when (navItem) {
                AboutPages.HOME -> AboutHome(
                    onTabChange = { page = it },
                )
                AboutPages.DONATE -> AboutDonate(
                    onBack = { page = AboutPages.HOME },
                )
                AboutPages.THIS_APP -> AboutThisApp(
                    onBack = { page = AboutPages.HOME },
                )
                AboutPages.LEGAL -> AboutLegal(
                    onBack = { page = AboutPages.HOME },
                )

                AboutPages.CREDITS -> AboutCredits(
                    onBack = { page = AboutPages.HOME },
                )
            }
        }
    }
}