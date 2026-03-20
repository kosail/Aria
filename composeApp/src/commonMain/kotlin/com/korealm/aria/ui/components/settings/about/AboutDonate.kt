package com.korealm.aria.ui.components.settings.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.donate
import com.korealm.aria.ui.components.misc.SimpleNavbar
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutDonate(
    onBack: () -> Unit,
    onExit: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        SimpleNavbar(
            title = stringResource(Res.string.donate),
            onBack = onBack,
            onClose = onExit
        )
    }
}