package com.korealm.aria.model

import kotlinx.serialization.Serializable

@Serializable
data class UserAudio(
    val id: Int,
    val title: String,
    val icon: String, // enum name
    val path: String
)