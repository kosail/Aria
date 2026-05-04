package com.korealm.aria.utils

import android.content.Context;
import java.io.File;

/**
 * Handles persistent storage of user audio file paths in the application's internal storage.
 *
 * @constructor Initializes the storage system by creating a reference to the storage file.
 * @param context The context used to access the application's internal storage.
 */
class UserAudioStorage(context: Context) {
    private val file = File(context.filesDir, "user_audio.txt")

    /**
     * Loads the list of audio file paths from the storage file.
     */
    fun load():List<String> {
        if (!file.exists()) return emptyList()
        return file.readLines().filter { it.isNotBlank() }
    }

    /**
     * Saves the given list of audio file paths to the storage file.
     */
    fun save(paths: List<String>) {
        file.writeText(paths.joinToString("\n"))
    }
}