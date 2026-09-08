package com.korealm.aria.utils

import com.korealm.aria.theme.AccentColor
import com.korealm.aria.theme.AccentColorScheme
import com.korealm.aria.theme.schemes.*


fun getColorScheme(accentColor: AccentColor, darkTheme: Boolean): AccentColorScheme {
    return when (accentColor) {
        AccentColor.RED -> if (darkTheme) redDarkScheme else redLightScheme
        AccentColor.PINK -> if (darkTheme) pinkDarkScheme else pinkLightScheme
        AccentColor.PURPLE -> if (darkTheme) purpleDarkScheme else purpleLightScheme
        AccentColor.BLUE -> if (darkTheme) blueDarkScheme else blueLightScheme
        AccentColor.GREEN -> if (darkTheme) greenDarkScheme else greenLightScheme
        AccentColor.YELLOW -> if (darkTheme) yellowDarkScheme else yellowLightScheme
    }
}