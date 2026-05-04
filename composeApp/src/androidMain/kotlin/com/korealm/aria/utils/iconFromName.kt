package com.korealm.aria.utils

import com.korealm.aria.data.CustomSoundIcons
import org.jetbrains.compose.resources.DrawableResource
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.heart_symbolic

/**
 * Retrieves a drawable resource corresponding to the provided icon name.
 * If the name does not match any entry in the `CustomSoundIcons` enum, a default icon is returned.
 *
 * @param name The name of the icon to retrieve, corresponding to an entry in the `CustomSoundIcons` enum.
 * @return The drawable resource associated with the specified icon name, or a default icon if the name is invalid.
 */
fun iconFromName(name: String): DrawableResource {
    return try {
        CustomSoundIcons.valueOf(name).icon
    } catch (_: Exception) {
        Res.drawable.heart_symbolic
    }
}