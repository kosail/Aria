package com.korealm.aria.state

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*

/**
 * Reactive facade for theme-related state consumed throughout the UI.
 *
 * [isDarkTheme] is derived from [SettingState]: if the user has explicitly set a preference
 * it uses that value, otherwise it falls back to the system default provided at creation time.
 *
 * This class exists so that the many UI components that read `LocalThemeState.current.isDarkTheme`
 * continue to work unchanged. Writes go through [SettingState.update] (the single source of truth).
 */
class AppThemeState(
    private val settingState: SettingState,
    private val systemDarkTheme: Boolean
) {
    /**
     * Resolved dark-theme flag: persisted preference if set, otherwise the system default.
     */
    val isDarkTheme: Boolean
        get() = settingState.currentSettings.isDarkTheme ?: systemDarkTheme

    /** Toggles the dark theme and persists the new value. */
    fun toggleTheme() {
        settingState.update { copy(isDarkTheme = !this@AppThemeState.isDarkTheme) }
    }
}

val LocalThemeState = staticCompositionLocalOf<AppThemeState> { error("No theme state provided") }

@Composable
fun rememberAppThemeState(
    settingState: SettingState,
    systemDarkTheme: Boolean = isSystemInDarkTheme(),
): AppThemeState = remember { AppThemeState(settingState, systemDarkTheme) }
