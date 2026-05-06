package com.korealm.aria.model

import com.korealm.aria.theme.AccentColor

/** User preference for light/dark theme resolution. */
enum class ThemeMode {
    /** Follow the OS/system theme. */
    SYSTEM,
    /** Always use dark theme. */
    DARK,
    /** Always use light theme. */
    LIGHT
}

data class AriaSettings(
    val accentColor: AccentColor = AccentColor.PURPLE,
    val themeMode: ThemeMode = ThemeMode.SYSTEM
)