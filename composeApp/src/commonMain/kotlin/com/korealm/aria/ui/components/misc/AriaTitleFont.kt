package com.korealm.aria.ui.components.misc

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import aria.composeapp.generated.resources.AlegreyaSansSC_Bold
import aria.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AriaTitleFont(
    stringRes: StringResource,
    fontSize: TextUnit = 48.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    letterSpacing: TextUnit = 2.sp,
    color: Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val titleFont = FontFamily(
        Font(Res.font.AlegreyaSansSC_Bold, weight = FontWeight.Bold)
    )

    Text(
        text = stringResource(stringRes),
        fontSize = fontSize,
        fontFamily = titleFont,
        fontWeight = fontWeight,
        letterSpacing = letterSpacing,
        color = color,
        modifier = modifier
    )
}