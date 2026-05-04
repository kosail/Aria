package com.korealm.aria.model

// This interface unifies the built-in audios with the user-added ones.
interface AudioRepository {
    suspend fun loadBuiltIn(): List<AudioResource>
    suspend fun loadUser(): List<AudioResource>
}