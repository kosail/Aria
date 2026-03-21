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
import aria.composeapp.generated.resources.donations_text
import aria.composeapp.generated.resources.no_donations_currently
import com.korealm.aria.ui.components.misc.Copyright
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

//                val paypalUrl = stringResource(Res.string.paypal_url)
//                GtkButton(
//                    onClick = { openUrl(paypalUrl) },
//                    modifier = Modifier
//                ) {
//                    LabelWithIcon(
//                        stringRes = Res.string.donate_on_paypal,
//                        iconRes = Res.drawable.external_link
//                    )
//                }

                Text(
                    text = stringResource(Res.string.no_donations_currently).trimIndent(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 16.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Copyright(modifier = Modifier.padding(bottom = 16.dp))
        }
    }
}