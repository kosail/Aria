package com.korealm.aria.utils

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.korealm.aria.model.UserAudio
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json

private val Context.dataStore by preferencesDataStore("user_audio")

class UserAudioDataStore(private val context: Context) {
    private val KEY = stringPreferencesKey("audios")
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun load(): List<UserAudio> {
        val prefs = context.dataStore.data.first()
        val raw = prefs[KEY] ?: return emptyList()
        return json.decodeFromString(raw)
    }

    suspend fun save(list: List<UserAudio>) {
        val encoded = json.encodeToString(list)
        context.dataStore.edit {
            it[KEY] = encoded
        }
    }
}