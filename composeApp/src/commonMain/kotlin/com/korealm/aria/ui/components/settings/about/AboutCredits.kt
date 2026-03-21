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
import aria.composeapp.generated.resources.*
import com.korealm.aria.ui.components.misc.SimpleNavbar
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
private fun SimpleTitle(
    titleRes: StringResource
) {
    Text(
        text = stringResource(titleRes),
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun AboutCredits(
    onBack: () -> Unit,
    onExit: () -> Unit
) {
    val soundsBy = listOf(
        stringResource(Res.string.credits_sounds_by_one),
        stringResource(Res.string.credits_sounds_by_two),
        stringResource(Res.string.credits_sounds_by_three),
        stringResource(Res.string.credits_sounds_by_four),
        stringResource(Res.string.credits_sounds_by_five),
        stringResource(Res.string.credits_sounds_by_six),
        stringResource(Res.string.credits_sounds_by_seven),
        stringResource(Res.string.credits_sounds_by_eight),
        stringResource(Res.string.credits_sounds_by_nine),
        stringResource(Res.string.credits_sounds_by_ten),
        stringResource(Res.string.credits_sounds_by_eleven),
        stringResource(Res.string.credits_sounds_by_twelve),
        stringResource(Res.string.credits_sounds_by_thirteen),
        stringResource(Res.string.credits_sounds_by_fourteen),
    )

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        SimpleNavbar(
            title = stringResource(Res.string.credits),
            onBack = onBack,
            onClose = onExit
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


            SimpleTitle(Res.string.credits_design_by)


            SimpleTitle(Res.string.credits_sounds_by)


            SimpleTitle(Res.string.credits_sounds_edited_by)

        }
    }
}