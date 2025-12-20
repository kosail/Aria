package com.korealm.aria.ui.components.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aria.composeapp.generated.resources.*
import com.korealm.aria.ui.components.misc.CustomDialog
import com.korealm.aria.ui.components.misc.GtkButton
import com.korealm.aria.ui.components.misc.LabelWithIcon
import com.korealm.aria.utils.openUrl
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Clock


@Composable
fun AboutDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier.padding(top = 40.dp, bottom = 24.dp)
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
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth()
            ) {
                GtkButton(
                    onClick = {},
                    modifier = Modifier
                ) {
                    LabelWithIcon(
                        stringRes = Res.string.about_this_app,
                        iconRes = Res.drawable.chevron_right
                    )
                }

                val issueUrl = stringResource(Res.string.report_issue_url)
                GtkButton(
                    onClick = { openUrl(issueUrl) },
                    modifier = Modifier
                ) {
                    LabelWithIcon(
                        stringRes = Res.string.report_an_issue,
                        iconRes = Res.drawable.external_link
                    )
                }

                GtkButton(
                    onClick = {},
                    modifier = Modifier
                ) {
                    LabelWithIcon(
                        stringRes = Res.string.donate,
                        iconRes = Res.drawable.chevron_right
                    )
                }
            }

            val year = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
            ) {
                Text(
                    text = "$year © ${stringResource(Res.string.kosail_in_korealm)}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )

                Text(
                    text = stringResource(Res.string.from_honduras),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
                )
            }

        }
    }
}