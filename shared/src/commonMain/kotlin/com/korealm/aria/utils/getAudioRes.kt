package com.korealm.aria.utils

import com.korealm.aria.shared.Target.DESKTOP
import com.korealm.aria.shared.getTargetPlatform


// This function was a remnant of when the JVM target existed, because it did not support OGG file format.
// I had to manually route to WAV format.
// TODO: Either remove this function or give the JVM target another chance.
fun getAudioRes(audioName: String): String {
    val uriPrefix = "composeResources/aria.shared.generated.resources/files/"
    val fileExt = ".ogg"

    return "$uriPrefix$audioName$fileExt"
}