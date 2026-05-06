package com.korealm.aria.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import com.korealm.aria.model.AriaSettings
import com.korealm.aria.theme.AccentColor
import com.russhwolf.settings.Settings

/**
 * Reactive bridge between the [Settings] persistence layer and the [AriaSettings] data model.
 *
 * Each [AriaSettings] field is stored as an individual typed key so the russhwolf settings library handles
 * serialization natively. The in-memory [currentSettings] is Compose-observable, so any composable reading
 * it will recompose automatically when [update] is called.
 *
 * @param settings The platform [Settings] instance. Defaults to [Settings] from the russhwofl settings no-arg
 * module, which resolves to SharedPreferences on Android, java.util.prefs.Preferences on JVM,
 * and localStorage on WasmJS.
 */
class SettingState(private val settings: Settings = Settings()) {

    /** The current in-memory settings, observable by Compose. */
    var currentSettings by mutableStateOf(load())
        private set

    /**
     * Reads all persisted keys and reconstructs an [AriaSettings] instance.
     * Unknown or missing keys fall back to [AriaSettings] defaults.
     */
    private fun load(): AriaSettings {
        val defaults = AriaSettings()

        val accentColor = settings.getString(Keys.ACCENT_COLOR, defaults.accentColor.name)
            .toAccentColorOrDefault(defaults.accentColor)

        val isDarkTheme = settings.getBooleanOrNull(Keys.IS_DARK_THEME)

        return AriaSettings(
            accentColor = accentColor,
            isDarkTheme = isDarkTheme
        )
    }

    /**
     * Applies a transformation to the current settings, persists any changed fields,
     * and updates the Compose-observable [currentSettings].
     *
     * Usage:
     * ```
     * settingState.update { copy(accentColor = AccentColor.RED) }
     * ```
     */
    fun update(transform: AriaSettings.() -> AriaSettings) {
        val updated = currentSettings.transform()
        persist(updated)
        currentSettings = updated
    }

    /**
     * Persists every field of the given [AriaSettings] to the [Settings] store.
     * Called on every [update]
     */
    private fun persist(ariaSettings: AriaSettings) {
        settings.putString(Keys.ACCENT_COLOR, ariaSettings.accentColor.name)

        val isDark = ariaSettings.isDarkTheme
        if (isDark != null) {
            settings.putBoolean(Keys.IS_DARK_THEME, isDark)
        } else {
            settings.remove(Keys.IS_DARK_THEME)
        }
    }

    /** Storage keys — private to prevent typo bugs across the codebase. */
    private object Keys {
        const val ACCENT_COLOR = "accent_color"
        const val IS_DARK_THEME = "is_dark_theme"
    }
}

/**
 * Safely parses an [AccentColor] from its [name], returning [default] if the value
 * doesn't match any enum entry (e.g. after removing a color in a future version).
 */
private fun String.toAccentColorOrDefault(default: AccentColor): AccentColor =
    AccentColor.entries.firstOrNull { it.name == this } ?: default

val LocalSettings = staticCompositionLocalOf<SettingState> {
    error("No SettingState provided — wrap your content with AppProvider")
}

@Composable
fun rememberSettingState(): SettingState = remember { SettingState() }
