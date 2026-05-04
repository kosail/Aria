package com.korealm.aria.ui.components.settings.customSoundEdit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aria.composeapp.generated.resources.*
import com.korealm.aria.ui.components.misc.GtkButton
import com.korealm.aria.ui.components.misc.LabelWithIcon
import com.korealm.aria.utils.GtkButtonShape

@Composable
fun SoundHomeActions(
    title: String,
    onTabChange: (CustomSoundPages) -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // TODO: Fix this issue on long texts
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .weight(1f)
                .padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    text = title.uppercase(),
                    style = MaterialTheme.typography.headlineLarge,
                    letterSpacing = 1.sp,
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 12.dp)
            ) {
                val pages = arrayOf(
                    CustomSoundPages.EDIT_NAME to Res.string.audio_edit_name,
                    CustomSoundPages.EDIT_ICON to Res.string.audio_edit_icon,
                    CustomSoundPages.DELETE to Res.string.audio_delete,
                )

                pages.forEachIndexed { index, (page, title) ->
                    val shape = if (index == 0) {
                        GtkButtonShape.TOP
                    } else {
                        if (index == pages.lastIndex) GtkButtonShape.BOTTOM else GtkButtonShape.MIDDLE
                    }

                    GtkButton(
                        onClick = { onTabChange(page) },
                        buttonShape = shape,
                        modifier = Modifier
                    ) {
                        LabelWithIcon(
                            stringRes = title,
                            iconRes = Res.drawable.chevron_right
                        )
                    }
                }
            }
        }
    }
}