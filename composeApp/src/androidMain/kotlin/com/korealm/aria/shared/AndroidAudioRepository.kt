package com.korealm.aria.shared

import com.korealm.aria.model.AudioRepository
import com.korealm.aria.model.AudioResource

class AndroidAudioRepository: AudioRepository {

    override suspend fun loadBuiltIn(): List<AudioResource> {
        return BuiltInAudioRepository().loadBuiltIn()
    }

    override suspend fun loadUser(): List<AudioResource> {
        // TODO: Implement this later, for now focus in making the built-in audios work again
        return BuiltInAudioRepository().loadUser()
    }
}