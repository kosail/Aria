package com.korealm.aria.shared

import android.content.Context
import android.net.Uri
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.audio_user_added
import aria.composeapp.generated.resources.heart_symbolic
import com.korealm.aria.model.AudioRepository
import com.korealm.aria.model.AudioResource
import com.korealm.aria.utils.UserAudioStorage
import com.korealm.aria.utils.copyToInternalStorage

/**
 * A repository for managing audio resources in an Android application. This class combines
 * built-in audio resources with user-added audio resources and provides functionality to
 * load and import them.
 *
 * @constructor Initializes the repository with a given application context.
 * @param context The context used to manage audio resources and storage operations.
 */
class AndroidAudioRepository(
    private val context: Context
): AudioRepository {
    private val storage = UserAudioStorage(context)

    override suspend fun loadBuiltIn(): List<AudioResource> {
        return BuiltInAudioRepository().loadBuiltIn()
    }

    /**
     * Loads the list of user-added audio resources by retrieving the audio file paths from storage,
     * and mapping each path to an AudioResource object. Each AudioResource includes a unique ID,
     * a default icon, a default title, and the corresponding file path.
     *
     * @return A list of AudioResource objects representing the user's audio files.
     */
    override suspend fun loadUser(): List<AudioResource> {
        return storage.load().mapIndexed { index, path ->
            AudioResource(
                id = 10_000 + index,
                icon = Res.drawable.heart_symbolic,
                title = Res.string.audio_user_added, // TODO: Make audio names customizable in the future
                audioPath = path
            )
        }
    }

    /**
     * Imports an audio file from the given URI into the application's internal storage. The file is copied
     * to a dedicated "audio" directory, and the file path is saved to the user's audio resources storage.
     *
     * @param uri The URI of the audio file to be imported.
     */
    suspend fun import(uri: Uri) {
        val path = copyToInternalStorage(context, uri)

        val current = storage.load().toMutableList()
        current.add(path)
        storage.save(current)
    }
}