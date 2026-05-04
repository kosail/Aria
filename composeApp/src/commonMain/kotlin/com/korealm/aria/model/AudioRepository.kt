package com.korealm.aria.model

import com.korealm.aria.data.CustomSoundIcons

// This interface unifies the built-in audios with the user-added ones.
interface AudioRepository {
    suspend fun loadBuiltIn(): List<AudioResource>
    suspend fun loadUser(): List<AudioResource>
    suspend fun updateIcon(id: Int, icon: CustomSoundIcons)
}