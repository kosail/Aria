package com.korealm.aria.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import aria.composeapp.generated.resources.*
import org.jetbrains.compose.resources.Font

@Composable
fun WhisperingNatureTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Whispering Nature Light Theme
    val WhisperLightPrimary = Color(0xFF4F6B57)           // Pine Green
    val WhisperLightPrimaryContainer = Color(0xFFDBE9DD)  // Soft Leaf

    val WhisperLightSecondary = Color(0xFF776B5F)         // Bark Brown
    val WhisperLightSecondaryContainer = Color(0xFFE1DED9) // Lichen

    val WhisperLightTertiary = Color(0xFF94A89A)          // Muted Fern
    val WhisperLightTertiaryContainer = Color(0xFFDCEAE1) // Pale Moss

    val WhisperLightBackground = Color(0xFFF5F4F1)         // Misty Cloud
    val WhisperLightSurface = Color(0xFFE8E6E3)            // Fogstone
    val WhisperLightSurfaceVariant = Color(0xFFDAD7D2)     // Weathered Stone

    val WhisperLightText = Color(0xFF3D3A35)               // Shadowed Bark
    val WhisperLightError = Color(0xFFAD5C5C)              // Dry Clay
    val WhisperLightErrorContainer = Color(0xFFF2E7E5)     // Sand Mist

    fun whisperingNatureLightColors() = lightColorScheme(
        primary = WhisperLightPrimary,
        onPrimary = WhisperLightPrimaryContainer,
        primaryContainer = WhisperLightPrimaryContainer,
        onPrimaryContainer = WhisperLightText,

        secondary = WhisperLightSecondary,
        onSecondary = WhisperLightSecondaryContainer,
        secondaryContainer = WhisperLightSecondaryContainer,
        onSecondaryContainer = WhisperLightText,

        tertiary = WhisperLightTertiary,
        onTertiary = WhisperLightTertiaryContainer,
        tertiaryContainer = WhisperLightTertiaryContainer,
        onTertiaryContainer = WhisperLightText,

        background = WhisperLightBackground,
        onBackground = WhisperLightText,

        surface = WhisperLightSurface,
        onSurface = WhisperLightText,
        surfaceVariant = WhisperLightSurfaceVariant,
        onSurfaceVariant = Color(0xFF5C5955), // Gentle outline contrast

        error = WhisperLightError,
        onError = WhisperLightErrorContainer,
        errorContainer = WhisperLightErrorContainer,
        onErrorContainer = WhisperLightText
    )



    // Whispering Nature Dark Theme
    val WhisperDarkPrimary = Color(0xFF94B59C)              // Soft Fern
    val WhisperDarkPrimaryContainer = Color(0xFF17241F)     // Forest Shadow

    val WhisperDarkSecondary = Color(0xFFA38C77)            // Autumn Bark
    val WhisperDarkSecondaryContainer = Color(0xFF2D1F14)   // Faded Lichen

    val WhisperDarkTertiary = Color(0xFF819284)             // Smoky Moss
    val WhisperDarkTertiaryContainer = Color(0xFF1F2C22)    // Forest Floor

    val WhisperDarkBackground = Color(0xFF0E1A16)           // Midnight Pine
    val WhisperDarkSurface = Color(0xFF1B2A24)              // Obsidian Moss
    val WhisperDarkSurfaceVariant = Color(0xFF2D3A34)       // Moss Shadow

    val WhisperDarkText = Color(0xFFE5E3DE)                 // Morning Dew
    val WhisperDarkError = Color(0xFFD08989)                // Rusted Leaf
    val WhisperDarkErrorContainer = Color(0xFF1F1110)       // Dark Clay Dust

    fun whisperingNatureDarkColors() = darkColorScheme(
        primary = WhisperDarkPrimary,
        onPrimary = WhisperDarkPrimaryContainer,
        primaryContainer = WhisperDarkPrimaryContainer,
        onPrimaryContainer = WhisperDarkText,

        secondary = WhisperDarkSecondary,
        onSecondary = WhisperDarkSecondaryContainer,
        secondaryContainer = WhisperDarkSecondaryContainer,
        onSecondaryContainer = WhisperDarkText,

        tertiary = WhisperDarkTertiary,
        onTertiary = WhisperDarkTertiaryContainer,
        tertiaryContainer = WhisperDarkTertiaryContainer,
        onTertiaryContainer = WhisperDarkText,

        background = WhisperDarkBackground,
        onBackground = WhisperDarkText,

        surface = WhisperDarkSurface,
        onSurface = WhisperDarkText,
        surfaceVariant = WhisperDarkSurfaceVariant,
        onSurfaceVariant = Color(0xFFC9C9C9),  // Subtle neutral border

        error = WhisperDarkError,
        onError = WhisperDarkErrorContainer,
        errorContainer = WhisperDarkErrorContainer,
        onErrorContainer = WhisperDarkText
    )


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
        colorScheme = if (darkTheme) whisperingNatureDarkColors() else whisperingNatureLightColors(),
        typography = defaultTypography,
        content = content
    )
}