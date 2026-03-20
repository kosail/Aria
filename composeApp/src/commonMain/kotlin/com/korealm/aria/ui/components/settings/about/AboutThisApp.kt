package com.korealm.aria.ui.components.settings.about

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aria.composeapp.generated.resources.*
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

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(Res.string.tribute_zero),
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(Res.string.tribute_one),
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(Res.string.tribute_two).trimIndent(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(16.dp)
            )

            Text(
                text = stringResource(Res.string.tribute_legal),
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = stringResource(Res.string.tribute_three).trimIndent(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 24.dp)
            )
        }
    }
}