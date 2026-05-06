package com.korealm.aria.state

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import com.korealm.aria.model.ThemeMode

/**
 * Reactive facade for theme-related state consumed throughout the UI.
 *
 * [isDarkTheme] is resolved from the persisted [ThemeMode] in [SettingState]:
 * - [ThemeMode.SYSTEM] → delegates to the OS dark-theme flag captured at creation time.
 * - [ThemeMode.DARK] → always `true`.
 * - [ThemeMode.LIGHT] → always `false`.
 *
 * This class exists so that the many UI components reading `LocalThemeState.current.isDarkTheme`
 * continue to work unchanged. Writes go through [SettingState.update] (the single source of truth).
 */
class AppThemeState(
    private val settingState: SettingState,
    private val systemDarkTheme: Boolean
) {
    /** Resolved dark-theme flag based on the current [ThemeMode]. */
    val isDarkTheme: Boolean
        get() = when (settingState.currentSettings.themeMode) {
            ThemeMode.SYSTEM -> systemDarkTheme
            ThemeMode.DARK -> true
            ThemeMode.LIGHT -> false
        }

    /** Sets the theme mode and persists it. */
    fun setThemeMode(mode: ThemeMode) {
        settingState.update { copy(themeMode = mode) }
    }
}

val LocalThemeState = staticCompositionLocalOf<AppThemeState> { error("No theme state provided") }

@Composable
fun rememberAppThemeState(
    settingState: SettingState,
    systemDarkTheme: Boolean = isSystemInDarkTheme(),
): AppThemeState = remember { AppThemeState(settingState, systemDarkTheme) }
