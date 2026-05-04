package com.korealm.aria.shared

import android.content.Context
import com.korealm.aria.model.AudioRepository
import com.korealm.aria.model.AudioResource

class AndroidAudioRepository(
    private val context: Context
) : AudioRepository {

    override suspend fun loadBuiltIn(): List<AudioResource> {
        return BuiltInAudioRepository().loadBuiltIn()
    }

    override suspend fun loadUser(): List<AudioResource> {
        return BuiltInAudioRepository().loadBuiltIn()
    }
}