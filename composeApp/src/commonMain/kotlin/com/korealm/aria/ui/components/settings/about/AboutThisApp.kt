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
import com.korealm.aria.shared.Target.WEB
import com.korealm.aria.shared.getTargetPlatform
import com.korealm.aria.shared.openUrl
import com.korealm.aria.ui.components.misc.Copyright
import com.korealm.aria.ui.components.misc.GtkButton
import com.korealm.aria.ui.components.misc.LabelWithIcon
import com.korealm.aria.ui.components.misc.SimpleNavbar
import com.korealm.aria.utils.GtkButtonShape
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutThisApp(
    onBack: () -> Unit,
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
                    text = stringResource(Res.string.tribute_zero),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(Res.string.tribute_one),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(Res.string.tribute_two).trimIndent(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(16.dp)
            )

            if (getTargetPlatform() != WEB) {
                GtkUrlButton(
                    urlResource = Res.string.web_version_url,
                    stringRes = Res.string.web_version_invite,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            GtkUrlButton(
                urlResource = Res.string.github_url,
                stringRes = Res.string.github_project,
                buttonShape = GtkButtonShape.TOP,
            )

            GtkUrlButton(
                urlResource = Res.string.codeberg_url,
                stringRes = Res.string.codeberg_project,
                buttonShape = GtkButtonShape.BOTTOM,
            )

            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.weight(1f))

            Copyright(modifier = Modifier.padding(bottom = 16.dp))
        }
    }
}

@Composable
private fun GtkUrlButton(
    urlResource: StringResource,
    stringRes: StringResource,
    iconRes: DrawableResource = Res.drawable.external_link,
    buttonShape: GtkButtonShape = GtkButtonShape.UNIQUE,
    modifier: Modifier = Modifier
) {
    val url = stringResource(urlResource)

    GtkButton(
        onClick = { openUrl(url) },
        buttonShape = buttonShape,
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        LabelWithIcon(
            stringRes = stringRes,
            iconRes = iconRes
        )
    }
}