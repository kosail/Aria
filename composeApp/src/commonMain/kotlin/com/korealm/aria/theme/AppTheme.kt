package com.korealm.aria.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import aria.composeapp.generated.resources.*
import com.korealm.aria.utils.getColorScheme
import org.jetbrains.compose.resources.Font

/**
 * Applies a consistent visual theme to the application using Material 3 design principles.
 *
 * This method allows for dynamic theming based on the selected accent color and theme mode
 * (light or dark). It also customizes typography using a predefined font family.
 * The content provided to this composable will inherit the defined theme.
 *
 * @param accentColor The main color used to style the application. Must be one of the predefined values in the AccentColor enum.
 * @param darkTheme A boolean indicating whether the dark theme should be applied. If true, a dark color scheme is used, otherwise a light color scheme is applied.
 * @param content A composable lambda function that represents the UI content to render within the theme.
 */
@Composable
fun AppTheme(
    accentColor: AccentColor,
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colorScheme = getColorScheme(accentColor, darkTheme)
    val theme = if (darkTheme) getDarkBase(colorScheme) else getLightBase(colorScheme)

    val fontFamily = FontFamily(
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
            displayLarge = displayLarge.copy(fontFamily = fontFamily),
            displayMedium = displayMedium.copy(fontFamily = fontFamily),
            displaySmall = displaySmall.copy(fontFamily = fontFamily),
            headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
            headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
            headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
            titleLarge = titleLarge.copy(fontFamily = fontFamily),
            titleMedium = titleMedium.copy(fontFamily = fontFamily),
            titleSmall = titleSmall.copy(fontFamily = fontFamily),
            bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
            bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
            bodySmall = bodySmall.copy(fontFamily = fontFamily),
            labelLarge = labelLarge.copy(fontFamily = fontFamily),
            labelMedium = labelMedium.copy(fontFamily = fontFamily),
            labelSmall = labelSmall.copy(fontFamily = fontFamily),
        )
    }

    MaterialTheme(
        colorScheme = theme,
        typography = defaultTypography,
        content = content
    )
}