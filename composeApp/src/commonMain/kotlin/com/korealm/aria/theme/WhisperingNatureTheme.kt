package com.korealm.aria.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color


// Whispering Nature Light Theme
val whisperNatureLightPrimary = Color(0xFF4F6B57)           // Pine Green
val whisperNatureLightPrimaryContainer = Color(0xFFDBE9DD)  // Soft Leaf

val whisperNatureLightSecondary = Color(0xFF776B5F)         // Bark Brown
val whisperNatureLightSecondaryContainer = Color(0xFFE1DED9) // Lichen

val whisperNatureLightTertiary = Color(0xFF94A89A)          // Muted Fern
val whisperNatureLightTertiaryContainer = Color(0xFFDCEAE1) // Pale Moss

val whisperNatureLightBackground = Color(0xFFF5F4F1)         // Misty Cloud
val whisperNatureLightSurface = Color(0xFFE8E6E3)            // Fogstone
val whisperNatureLightSurfaceVariant = Color(0xFFDAD7D2)     // Weathered Stone

val whisperNatureLightText = Color(0xFF3D3A35)               // Shadowed Bark
val whisperNatureLightError = Color(0xFFAD5C5C)              // Dry Clay
val whisperNatureLightErrorContainer = Color(0xFFF2E7E5)     // Sand Mist

fun whisperingNatureLightColors() = lightColorScheme(
    primary = whisperNatureLightPrimary,
    onPrimary = whisperNatureLightPrimaryContainer,
    primaryContainer = whisperNatureLightPrimaryContainer,
    onPrimaryContainer = whisperNatureLightText,

    secondary = whisperNatureLightSecondary,
    onSecondary = whisperNatureLightSecondaryContainer,
    secondaryContainer = whisperNatureLightSecondaryContainer,
    onSecondaryContainer = whisperNatureLightText,

    tertiary = whisperNatureLightTertiary,
    onTertiary = whisperNatureLightTertiaryContainer,
    tertiaryContainer = whisperNatureLightTertiaryContainer,
    onTertiaryContainer = whisperNatureLightText,

    background = whisperNatureLightBackground,
    onBackground = whisperNatureLightText,

    surface = whisperNatureLightSurface,
    onSurface = whisperNatureLightText,
    surfaceVariant = whisperNatureLightSurfaceVariant,
    onSurfaceVariant = Color(0xFF5C5955), // Gentle outline contrast

    error = whisperNatureLightError,
    onError = whisperNatureLightErrorContainer,
    errorContainer = whisperNatureLightErrorContainer,
    onErrorContainer = whisperNatureLightText
)



// Whispering Nature Dark Theme
val whisperNatureDarkPrimary = Color(0xFF94B59C)              // Soft Fern
val whisperNatureDarkPrimaryContainer = Color(0xFF17241F)     // Forest Shadow

val whisperNatureDarkSecondary = Color(0xFFA38C77)            // Autumn Bark
val whisperNatureDarkSecondaryContainer = Color(0xFF2D1F14)   // Faded Lichen

val whisperNatureDarkTertiary = Color(0xFF819284)             // Smoky Moss
val whisperNatureDarkTertiaryContainer = Color(0xFF1F2C22)    // Forest Floor

val whisperNatureDarkBackground = Color(0xFF0E1A16)           // Midnight Pine
val whisperNatureDarkSurface = Color(0xFF1B2A24)              // Obsidian Moss
val whisperNatureDarkSurfaceVariant = Color(0xFF2D3A34)       // Moss Shadow

val whisperNatureDarkText = Color(0xFFE5E3DE)                 // Morning Dew
val whisperNatureDarkError = Color(0xFFD08989)                // Rusted Leaf
val whisperNatureDarkErrorContainer = Color(0xFF1F1110)       // Dark Clay Dust

fun whisperingNatureDarkColors() = darkColorScheme(
    primary = whisperNatureDarkPrimary,
    onPrimary = whisperNatureDarkPrimaryContainer,
    primaryContainer = whisperNatureDarkPrimaryContainer,
    onPrimaryContainer = whisperNatureDarkText,

    secondary = whisperNatureDarkSecondary,
    onSecondary = whisperNatureDarkSecondaryContainer,
    secondaryContainer = whisperNatureDarkSecondaryContainer,
    onSecondaryContainer = whisperNatureDarkText,

    tertiary = whisperNatureDarkTertiary,
    onTertiary = whisperNatureDarkTertiaryContainer,
    tertiaryContainer = whisperNatureDarkTertiaryContainer,
    onTertiaryContainer = whisperNatureDarkText,

    background = whisperNatureDarkBackground,
    onBackground = whisperNatureDarkText,

    surface = whisperNatureDarkSurface,
    onSurface = whisperNatureDarkText,
    surfaceVariant = whisperNatureDarkSurfaceVariant,
    onSurfaceVariant = Color(0xFFC9C9C9),  // Subtle neutral border

    error = whisperNatureDarkError,
    onError = whisperNatureDarkErrorContainer,
    errorContainer = whisperNatureDarkErrorContainer,
    onErrorContainer = whisperNatureDarkText
)