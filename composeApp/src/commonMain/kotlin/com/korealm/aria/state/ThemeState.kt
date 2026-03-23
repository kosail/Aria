package com.korealm.aria.state

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import com.korealm.aria.theme.AccentColor

class AppThemeState(initialDarkTheme: Boolean, accentColorScheme: AccentColor) {
    var isDarkTheme by mutableStateOf(initialDarkTheme)
        private set

    var accentColor by mutableStateOf(accentColorScheme)
        private set

    fun toggleTheme() { isDarkTheme = !isDarkTheme }
    fun setAccent(color: AccentColor) { accentColor = color }
}

val LocalThemeState = staticCompositionLocalOf<AppThemeState> { error("No theme state provided") }

@Composable
fun rememberAppThemeState(
    initialDarkTheme: Boolean = isSystemInDarkTheme(),
    accentColorScheme: AccentColor = AccentColor.BLUE
): AppThemeState = remember { AppThemeState(initialDarkTheme, accentColorScheme) }
