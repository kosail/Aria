package com.korealm.aria.ui.components.settings.about

import androidx.compose.foundation.layout.*
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
import aria.composeapp.generated.resources.donate
import aria.composeapp.generated.resources.donate_on_kofi
import aria.composeapp.generated.resources.donations_text
import aria.composeapp.generated.resources.external_link
import aria.composeapp.generated.resources.kofi_url
import com.korealm.aria.shared.openUrl
import com.korealm.aria.ui.components.misc.Copyright
import com.korealm.aria.ui.components.misc.GtkButton
import com.korealm.aria.ui.components.misc.LabelWithIcon
import com.korealm.aria.ui.components.misc.SimpleNavbar
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutDonate(
    onBack: () -> Unit,
) {

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        SimpleNavbar(
            title = stringResource(Res.string.donate),
            onBack = onBack,
        )

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Text(
                    text = stringResource(Res.string.donations_text).trimIndent(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )

                val kofiUrl = stringResource(Res.string.kofi_url)
                GtkButton(
                    onClick = { openUrl(kofiUrl) },
                    modifier = Modifier
                ) {
                    LabelWithIcon(
                        stringRes = Res.string.donate_on_kofi,
                        iconRes = Res.drawable.external_link
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Copyright(modifier = Modifier.padding(bottom = 16.dp))
        }
    }
}