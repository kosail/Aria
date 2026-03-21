package com.korealm.aria.ui.components.settings.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.license_legal
import aria.composeapp.generated.resources.license_legal_message
import com.korealm.aria.ui.components.misc.SimpleNavbar
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutLegal(
    onBack: () -> Unit,
    onExit: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        SimpleNavbar(
            title = stringResource(Res.string.license_legal),
            onBack = onBack,
            onClose = onExit
        )

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 24.dp)
        ) {
            Text(
                text = stringResource(Res.string.license_legal_message).trimIndent(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}