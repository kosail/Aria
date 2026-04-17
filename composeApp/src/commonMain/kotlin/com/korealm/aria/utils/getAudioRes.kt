package com.korealm.aria.utils

import com.korealm.aria.shared.Target.DESKTOP
import com.korealm.aria.shared.getTargetPlatform

fun getAudioRes(audioName: String): String {
    // The desktop version is the only one that loads files using the official Compose Multiplatform way of doing things
    // Web target and Android just pull the resource like a URI.
    // Check: https://kotlinlang.org/docs/multiplatform/compose-multiplatform-resources-usage.html#raw-files
    val uriPrefix = if (getTargetPlatform() == DESKTOP) {
        "composeResources/aria.composeapp.generated.resources/files/"
    } else {
        "assets/audios/"
    }

    // Desktop version does not support OGG file format
    val fileExt = if (getTargetPlatform() == DESKTOP) ".wav" else ".ogg"

    return "$uriPrefix$audioName$fileExt"
}