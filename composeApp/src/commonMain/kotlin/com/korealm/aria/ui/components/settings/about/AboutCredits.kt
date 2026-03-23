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
import aria.composeapp.generated.resources.*
import com.korealm.aria.ui.components.misc.GtkCard
import com.korealm.aria.ui.components.misc.SimpleNavbar
import com.korealm.aria.utils.GtkButtonShape
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
private fun SimpleTitle(
    titleRes: StringResource,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(titleRes),
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        modifier = modifier.padding(bottom = 16.dp)
    )
}

@Composable
private fun SimpleText(
    stringRes: StringResource,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(stringRes),
        fontSize = 16.sp,
        fontWeight = FontWeight.Light,
        modifier = modifier
    )
}

@Composable
fun AboutCredits(
    onBack: () -> Unit
) {
    val soundsBy = listOf(
        Res.string.credits_sounds_by_one,
        Res.string.credits_sounds_by_two,
        Res.string.credits_sounds_by_three,
        Res.string.credits_sounds_by_four,
        Res.string.credits_sounds_by_five,
        Res.string.credits_sounds_by_six,
        Res.string.credits_sounds_by_seven,
        Res.string.credits_sounds_by_eight,
        Res.string.credits_sounds_by_nine,
        Res.string.credits_sounds_by_ten,
        Res.string.credits_sounds_by_eleven,
        Res.string.credits_sounds_by_twelve,
        Res.string.credits_sounds_by_thirteen,
        Res.string.credits_sounds_by_fourteen,
    )

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        SimpleNavbar(
            title = stringResource(Res.string.credits),
            onBack = onBack,
        )

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 16.dp, bottom = 24.dp)
                .padding(horizontal = 24.dp)
        ) {
            SimpleTitle(Res.string.credits_code_by)
            GtkCard(
                modifier = Modifier.padding(bottom = 16.dp),
                content = { SimpleText(Res.string.credits_kosail) }
            )

            SimpleTitle(Res.string.credits_design_by)
            GtkCard(
                buttonShape = GtkButtonShape.TOP,
                content = { SimpleText(Res.string.credits_design_by_one) }
            )

            GtkCard(
                buttonShape = GtkButtonShape.MIDDLE,
                content = { SimpleText(Res.string.credits_design_by_two) }
            )


            GtkCard(
                modifier = Modifier.padding(bottom = 16.dp),
                buttonShape = GtkButtonShape.BOTTOM,
                content = { SimpleText(Res.string.credits_design_by_three) }
            )


            SimpleTitle(Res.string.credits_sounds_by)
            soundsBy.forEach { sound ->
                val shape = when(soundsBy.indexOf(sound)) {
                    0 -> GtkButtonShape.TOP
                    soundsBy.size - 1 -> GtkButtonShape.BOTTOM
                    else -> GtkButtonShape.MIDDLE
                }

                GtkCard(
                    buttonShape = shape,
                    content = { SimpleText(sound) }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            SimpleTitle(Res.string.credits_sounds_edited_by)
            GtkCard(
                content = { SimpleText(Res.string.credits_sounds_edited_by_one) },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .height(60.dp)
            )
        }
    }
}