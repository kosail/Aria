package com.korealm.aria.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Light theme section
// -------------------
val lightBackground = Color(0xFFFAFAFA)        // macOS-style Off White
val lightSurface = Color(0xFFF3F3F4)           // Very Light Gray
val lightSurfaceVariant = Color(0xFFE0E0E2)    // Neutral Divider Gray
val lightOnSurfaceVariant = Color(0xFF5F5F60)  // Neutral outline

val lightText = Color(0xFF2C2C2E)              // Deep Charcoal
val lightError = Color(0xFFD27A7A)             // Soft Red Clay
val lightErrorContainer = Color(0xFFF9EAEA)    // Blush Pink

fun getLightBase(accentColorScheme: AccentColorScheme) = lightColorScheme(
    primary = Color(accentColorScheme.primary),
    onPrimary = lightText,
    primaryContainer = Color(accentColorScheme.primaryContainer),
    onPrimaryContainer = lightText,

    secondary = Color(accentColorScheme.secondary),
    onSecondary = lightText,
    secondaryContainer = Color(accentColorScheme.secondaryContainer),
    onSecondaryContainer = lightText,

    tertiary = Color(accentColorScheme.tertiary),
    onTertiary = lightText,
    tertiaryContainer = Color(accentColorScheme.tertiaryContainer),
    onTertiaryContainer = lightText,

    background = lightBackground,
    onBackground = lightText,

    surface = lightSurface,
    onSurface = lightText,
    surfaceVariant = lightSurfaceVariant,
    onSurfaceVariant = lightOnSurfaceVariant,

    error = lightError,
    onError = lightText,
    errorContainer = lightErrorContainer,
    onErrorContainer = lightText
)


// Dark theme section
// -------------------
val darkBackground = Color(0xFF121416)         // Almost Black
val darkSurface = Color(0xFF1C1E21)            // Graphite Gray
val darkSurfaceVariant = Color(0xFF2C2E31)     // Subdued Divider Gray
val darkOnSurfaceVariant = Color(0xFFAAAAAA)   // Subtle light divider

val darkText = Color(0xFFE6E6E8)               // Mist White
val darkError = Color(0xFFE18C8C)              // Warm Muted Red
val darkErrorContainer = Color(0xFF3B2B2B)     // Deep Red Clay

fun getDarkBase(accentColorScheme: AccentColorScheme) = darkColorScheme(
    primary = Color(accentColorScheme.primary),
    onPrimary = darkText,
    primaryContainer = Color(accentColorScheme.primaryContainer),
    onPrimaryContainer = darkText,

    secondary = Color(accentColorScheme.secondary),
    onSecondary = darkText,
    secondaryContainer = Color(accentColorScheme.secondaryContainer),
    onSecondaryContainer = darkText,

    tertiary = Color(accentColorScheme.tertiary),
    onTertiary = darkText,
    tertiaryContainer = Color(accentColorScheme.tertiaryContainer),
    onTertiaryContainer = darkText,

    background = darkBackground,
    onBackground = darkText,

    surface = darkSurface,
    onSurface = darkText,
    surfaceVariant = darkSurfaceVariant,
    onSurfaceVariant = darkOnSurfaceVariant,

    error = darkError,
    onError = darkText,
    errorContainer = darkErrorContainer,
    onErrorContainer = darkText
)