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
    val WhisperLightPrimary = Color(0xFFBACDDC)           // Misty Blue-Grey
    val WhisperLightPrimaryContainer = Color(0xFFDCE4EC)  // Lighter mist blue for cards

    val WhisperLightSecondary = Color(0xFFD9A5A5)         // Warm Clay Rose
    val WhisperLightSecondaryContainer = Color(0xFFF1DADA)

    val WhisperLightTertiary = Color(0xFFA8BCA1)          // Moss Green
    val WhisperLightTertiaryContainer = Color(0xFFDDEBD6)

    val WhisperLightBackground = Color(0xFFF4F6F8)         // Soft Cloud White
    val WhisperLightSurface = Color.White                 // Clean white surface
    val WhisperLightSurfaceVariant = Color(0xFFEAEFF3)    // Border / control background

    val WhisperLightText = Color(0xFF1A2A3A)              // Deep Ink Blue
    val WhisperLightError = Color(0xFFD13438)             // Subtle error red
    val WhisperLightErrorContainer = Color(0xFFFFDAD6)    // Light red background

    fun whisperingNatureLightColors() = lightColorScheme(
        primary = WhisperLightPrimary,
        onPrimary = Color.White,
        primaryContainer = WhisperLightPrimaryContainer,
        onPrimaryContainer = WhisperLightText,

        secondary = WhisperLightSecondary,
        onSecondary = Color.White,
        secondaryContainer = WhisperLightSecondaryContainer,
        onSecondaryContainer = WhisperLightText,

        tertiary = WhisperLightTertiary,
        onTertiary = Color.White,
        tertiaryContainer = WhisperLightTertiaryContainer,
        onTertiaryContainer = WhisperLightText,

        background = WhisperLightBackground,
        onBackground = WhisperLightText,

        surface = WhisperLightSurface,
        onSurface = WhisperLightText,
        surfaceVariant = WhisperLightSurfaceVariant,
        onSurfaceVariant = Color(0xFF444444),  // Gentle outline contrast

        error = WhisperLightError,
        onError = Color.White,
        errorContainer = WhisperLightErrorContainer,
        onErrorContainer = WhisperLightText
    )


    // Whispering Nature Dark Theme
    val WhisperDarkPrimary = Color(0xFFBACDDC)              // Same mist blue, softened for dark
    val WhisperDarkPrimaryContainer = Color(0xFF2E3A48)     // Rich cool container (mica-dark tone)

    val WhisperDarkSecondary = Color(0xFFD9A5A5)            // Soft rose stays effective in dark
    val WhisperDarkSecondaryContainer = Color(0xFF3D2F30)

    val WhisperDarkTertiary = Color(0xFFA8BCA1)             // Calming green
    val WhisperDarkTertiaryContainer = Color(0xFF2D3A2D)

    val WhisperDarkBackground = Color(0xFF1F2B38)           // Charcoal blue, not full black
    val WhisperDarkSurface = Color(0xFF2A3542)              // Slightly lighter surface
    val WhisperDarkSurfaceVariant = Color(0xFF3A495A)       // Button background or card borders

    val WhisperDarkText = Color(0xFFF5F5F5)                 // Soft white
    val WhisperDarkError = Color(0xFFFF5F5F)                // Bright error red
    val WhisperDarkErrorContainer = Color(0xFF5A1B1B)       // Dark red container

    fun whisperingNatureDarkColors() = darkColorScheme(
        primary = WhisperDarkPrimary,
        onPrimary = Color.Black,
        primaryContainer = WhisperDarkPrimaryContainer,
        onPrimaryContainer = WhisperDarkText,

        secondary = WhisperDarkSecondary,
        onSecondary = Color.Black,
        secondaryContainer = WhisperDarkSecondaryContainer,
        onSecondaryContainer = WhisperDarkText,

        tertiary = WhisperDarkTertiary,
        onTertiary = Color.Black,
        tertiaryContainer = WhisperDarkTertiaryContainer,
        onTertiaryContainer = WhisperDarkText,

        background = WhisperDarkBackground,
        onBackground = WhisperDarkText,

        surface = WhisperDarkSurface,
        onSurface = WhisperDarkText,
        surfaceVariant = WhisperDarkSurfaceVariant,
        onSurfaceVariant = Color(0xFFC9C9C9),  // Subtle neutral border

        error = WhisperDarkError,
        onError = Color.Black,
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

    val DefaultTypography = Typography().run {
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
        typography = DefaultTypography,
        content = content
    )
}