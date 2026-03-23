package com.korealm.aria.theme

enum class AccentColor {
    RED,
    PINK,
    PURPLE,
    BLUE,
    GREEN,
    YELLOW,
}

data class AccentColorScheme(
    val primary: Long,
    val primaryContainer: Long,
    val secondary: Long,
    val secondaryContainer: Long,
    val tertiary: Long,
    val tertiaryContainer: Long
)