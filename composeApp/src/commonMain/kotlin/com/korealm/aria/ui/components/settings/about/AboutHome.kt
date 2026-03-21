package com.korealm.aria.ui.components.settings.about

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aria.composeapp.generated.resources.*
import com.korealm.aria.shared.Target
import com.korealm.aria.shared.getTargetPlatform
import com.korealm.aria.shared.openUrl
import com.korealm.aria.ui.components.misc.Copyright
import com.korealm.aria.ui.components.misc.GtkButton
import com.korealm.aria.ui.components.misc.LabelWithIcon
import com.korealm.aria.utils.GtkButtonShape
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutHome(
    onTabChange: (AboutPages) -> Unit,
    onExit: () -> Unit
) {
    Column {
        if (getTargetPlatform() != Target.ANDROID) {
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(56.dp)
                        .width(56.dp)
                        .clip(RoundedCornerShape(10))
                        .clickable { onExit() }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.close),
                        contentDescription = stringResource(Res.string.close_menu),
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier.padding(top = 40.dp, bottom = 18.dp)
            ) {
                Image(
                    painter = painterResource(Res.drawable.favicon),
                    contentDescription = stringResource(Res.string.aria),
                    modifier = Modifier.size(112.dp)
                )
            }

            Text(
                text = stringResource(Res.string.aria),
                style = MaterialTheme.typography.headlineLarge,
                letterSpacing = 1.sp,
            )

            Text(
                text = stringResource(Res.string.kosail),
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(width = 80.dp, height = 32.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Text(
                    text = stringResource(Res.string.version),
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 12.dp)
            ) {
                GtkButton(
                    onClick = { onTabChange(AboutPages.THIS_APP) },
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    LabelWithIcon(
                        stringRes = Res.string.about_this_app,
                        iconRes = Res.drawable.chevron_right
                    )
                }

                val issueUrl = stringResource(Res.string.report_issue_url)
                GtkButton(
                    onClick = { openUrl(issueUrl) },
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    LabelWithIcon(
                        stringRes = Res.string.report_an_issue,
                        iconRes = Res.drawable.external_link
                    )
                }

                GtkButton(
                    onClick = { onTabChange(AboutPages.DONATE) },
                    buttonShape = GtkButtonShape.TOP,
                    modifier = Modifier
                ) {
                    LabelWithIcon(
                        stringRes = Res.string.donate,
                        iconRes = Res.drawable.chevron_right
                    )
                }

                GtkButton(
                    onClick = { onTabChange(AboutPages.CREDITS) },
                    buttonShape = GtkButtonShape.MIDDLE,
                    modifier = Modifier
                ) {
                    LabelWithIcon(
                        stringRes = Res.string.credits,
                        iconRes = Res.drawable.chevron_right
                    )
                }

                GtkButton(
                    onClick = { onTabChange(AboutPages.LEGAL) },
                    buttonShape = GtkButtonShape.BOTTOM,
                    modifier = Modifier
                ) {
                    LabelWithIcon(
                        stringRes = Res.string.license_legal,
                        iconRes = Res.drawable.chevron_right
                    )
                }
            }

            Copyright(modifier = Modifier.padding(bottom = 16.dp))
        }
    }
}