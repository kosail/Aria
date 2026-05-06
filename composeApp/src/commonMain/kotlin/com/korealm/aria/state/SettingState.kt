package com.korealm.aria.state

import androidx.compose.runtime.*
import com.korealm.aria.model.AriaSettings
import com.russhwolf.settings.Settings

/**
 * Reactive bridge between the [Settings] persistence layer and the [AriaSettings] data model.
 *
 * Each [AriaSettings] field is stored as an individual typed key so the russhwolf settings library handles
 * serialization natively. The in-memory [currentSettings] is Compose-observable, so any composable reading
 * it will recompose automatically when [update] is called.
 *
 * @param settings The platform [Settings] instance. Defaults to [Settings] from the no-arg
 * module, which resolves to SharedPreferences on Android, java.util.prefs.Preferences on JVM,
 * and localStorage on WasmJS according to official docs
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
            .toEnumOrDefault(defaults.accentColor)

        val themeMode = settings.getString(Keys.THEME_MODE, defaults.themeMode.name)
            .toEnumOrDefault(defaults.themeMode)

        return AriaSettings(
            accentColor = accentColor,
            themeMode = themeMode
        )
    }

    /**
     * Applies a transformation to the current settings, persists any changed fields,
     * and updates the Compose-observable [currentSettings].
     *
     * Usage:
     * ```
     * settingState.update { copy(accentColor = AccentColor.RED) }
     * settingState.update { copy(themeMode = ThemeMode.DARK) }
     * ```
     */
    fun update(transform: AriaSettings.() -> AriaSettings) {
        val updated = currentSettings.transform()
        persist(updated)
        currentSettings = updated
    }

    /**
     * Persists every field of the given [AriaSettings] to the [Settings] store.
     * Called on every [update].
     */
    private fun persist(ariaSettings: AriaSettings) {
        settings.putString(Keys.ACCENT_COLOR, ariaSettings.accentColor.name)
        settings.putString(Keys.THEME_MODE, ariaSettings.themeMode.name)
    }

    /** Storage keys — private to prevent typo bugs across the codebase. */
    private object Keys {
        const val ACCENT_COLOR = "accent_color"
        const val THEME_MODE = "theme_mode"
    }
}

/**
 * Safely parses an enum from its [name], returning [default] if the value
 * doesn't match any entry (e.g. after removing an entry in a future version).
 */
private inline fun <reified T : Enum<T>> String.toEnumOrDefault(default: T): T =
    enumValues<T>().firstOrNull { it.name == this } ?: default

val LocalSettings = staticCompositionLocalOf<SettingState> {
    error("No SettingState provided — wrap your content with AppProvider")
}

@Composable
fun rememberSettingState(): SettingState = remember { SettingState() }
