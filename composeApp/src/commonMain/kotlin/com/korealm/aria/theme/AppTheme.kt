package com.korealm.aria.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import aria.composeapp.generated.resources.AlegreyaSans_Black
import aria.composeapp.generated.resources.AlegreyaSans_Bold
import aria.composeapp.generated.resources.AlegreyaSans_ExtraBold
import aria.composeapp.generated.resources.AlegreyaSans_Light
import aria.composeapp.generated.resources.AlegreyaSans_Medium
import aria.composeapp.generated.resources.AlegreyaSans_Regular
import aria.composeapp.generated.resources.AlegreyaSans_Thin
import aria.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun AppTheme(
    theme: Theme = Theme.WhisperingSea,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when (theme) {
        Theme.WhisperingSea -> if (darkTheme) whisperingSeaDarkColors() else whisperingSeaLightColors()
        Theme.WhisperingNature -> if (darkTheme) whisperingNatureDarkColors() else whisperingNatureLightColors()
    }

    val alegreyaSansFont = FontFamily(
        Font(Res.font.AlegreyaSans_Black, weight = FontWeight.Black),
        Font(Res.font.AlegreyaSans_Bold, weight = FontWeight.Bold),
        Font(Res.font.AlegreyaSans_ExtraBold, weight = FontWeight.ExtraBold),
        Font(Res.font.AlegreyaSans_Light, weight = FontWeight.Light),
        Font(Res.font.AlegreyaSans_Medium, weight = FontWeight.Medium),
        Font(Res.font.AlegreyaSans_Regular, weight = FontWeight.Normal),
        Font(Res.font.AlegreyaSans_Thin, weight = FontWeight.Thin)
    )

    val defaultTypography = androidx.compose.material3.Typography().run {
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
        colorScheme = colorScheme,
        typography = defaultTypography,
        content = content
    )
}