package com.korealm.aria.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import aria.composeapp.generated.resources.*
import com.korealm.aria.utils.getColorScheme
import org.jetbrains.compose.resources.Font

@Composable
fun AppTheme(
    accentColor: AccentColor = AccentColor.BLUE,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = getColorScheme(accentColor, darkTheme)
    val theme = if (darkTheme) getDarkBase(colorScheme) else getLightBase(colorScheme)

    val alegreyaSansFont = FontFamily(
        Font(Res.font.AlegreyaSans_Black, weight = FontWeight.Black),
        Font(Res.font.AlegreyaSans_Bold, weight = FontWeight.Bold),
        Font(Res.font.AlegreyaSans_ExtraBold, weight = FontWeight.ExtraBold),
        Font(Res.font.AlegreyaSans_Light, weight = FontWeight.Light),
        Font(Res.font.AlegreyaSans_Medium, weight = FontWeight.Medium),
        Font(Res.font.AlegreyaSans_Regular, weight = FontWeight.Normal),
        Font(Res.font.AlegreyaSans_Thin, weight = FontWeight.Thin)
    )

    val defaultTypography = Typography().run {
        Typography(
            displayLarge = displayLarge.copy(fontFamily = alegreyaSansFont),
            displayMedium = displayMedium.copy(fontFamily = alegreyaSansFont),
            displaySmall = displaySmall.copy(fontFamily = alegreyaSansFont),
            headlineLarge = headlineLarge.copy(fontFamily = alegreyaSansFont),
            headlineMedium = headlineMedium.copy(fontFamily = alegreyaSansFont),
            headlineSmall = headlineSmall.copy(fontFamily = alegreyaSansFont),
            titleLarge = titleLarge.copy(fontFamily = alegreyaSansFont),
            titleMedium = titleMedium.copy(fontFamily = alegreyaSansFont),
            titleSmall = titleSmall.copy(fontFamily = alegreyaSansFont),
            bodyLarge = bodyLarge.copy(fontFamily = alegreyaSansFont),
            bodyMedium = bodyMedium.copy(fontFamily = alegreyaSansFont),
            bodySmall = bodySmall.copy(fontFamily = alegreyaSansFont),
            labelLarge = labelLarge.copy(fontFamily = alegreyaSansFont),
            labelMedium = labelMedium.copy(fontFamily = alegreyaSansFont),
            labelSmall = labelSmall.copy(fontFamily = alegreyaSansFont),
        )
    }

    MaterialTheme(
        colorScheme = theme,
        typography = defaultTypography,
        content = content
    )
}