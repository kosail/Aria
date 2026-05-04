package com.korealm.aria.shared

import android.content.Context
import android.net.Uri
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.audio_user_added
import com.korealm.aria.data.CustomSoundIcons
import com.korealm.aria.model.AudioRepository
import com.korealm.aria.model.AudioResource
import com.korealm.aria.model.UserAudio
import com.korealm.aria.utils.UserAudioDataStore
import com.korealm.aria.utils.copyToInternalStorage
import com.korealm.aria.utils.iconFromName
import org.jetbrains.compose.resources.getString

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
    private val storage = UserAudioDataStore(context)

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
        return storage.load().map {
            AudioResource(
                id = it.id,
                icon = iconFromName(it.icon),
                title = it.title,
                audioPath = it.path
            )
        }
    }

    /**
     * Imports a user-selected audio file into the application's internal storage
     * and adds it to the list of user-defined audio resources.
     *
     * @param uri The URI of the audio file to be imported.
     */
    suspend fun import(uri: Uri) {
        val path = copyToInternalStorage(context, uri)

        val current = storage.load().toMutableList()

        val id = generateId(current)
        val new = UserAudio(
            id = id,
            title = getString(Res.string.audio_user_added) + " ${id - 10_000 + 1}", // Simple numeration
            icon = CustomSoundIcons.HEART.name, // The default icon is the heart one, coz yeah
            path = "file://$path"
        )

        current.add(new)
        storage.save(current)
    }

    /**
     * Generates a unique ID for a new user-defined audio resource.
     * The ID is based on the maximum ID in the list of user-defined audio resources.
     * If there are no user-defined audio resources, the ID is set to 10_000.
     *
     * User audios start from index 10_000, so we have reserved 10_000 positions for built-in sounds.
     * Index size is not an issue, so I think it's fine to reserve a lot of space before starting with user audios.
     *
     * @param list The list of user-defined audio resources.
     * @return The generated unique ID.
     */
    private fun generateId(list: List<UserAudio>): Int {
        return (list.maxOfOrNull { it.id } ?: 10_000) + 1
    }

    /**
     * Updates the icon of a specific user-defined audio resource.
     *
     * @param id The unique identifier of the audio resource to update.
     * @param icon The new icon to set for the audio resource, represented as a value from the CustomSoundIcons enum.
     */
    suspend fun updateIcon(id: Int, icon: CustomSoundIcons) {
        val current = storage.load().toMutableList()

        val index = current.indexOfFirst { it.id == id }
        if (index == -1) return

        current[index] = current[index].copy(
            icon = icon.name
        )

        storage.save(current)
    }
}