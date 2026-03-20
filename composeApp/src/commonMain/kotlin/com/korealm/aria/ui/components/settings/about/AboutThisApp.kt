package com.korealm.aria.ui.components.settings.about

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.about_this_app
import aria.composeapp.generated.resources.tribute
import aria.composeapp.generated.resources.tribute_two
import com.korealm.aria.ui.components.misc.SimpleNavbar
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutThisApp(
    onBack: () -> Unit,
    onExit: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        SimpleNavbar(
            title = stringResource(Res.string.about_this_app),
            onBack = onBack,
            onClose = onExit
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(Res.string.tribute).trimIndent(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(Res.string.tribute_two).trimIndent(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

    }
}