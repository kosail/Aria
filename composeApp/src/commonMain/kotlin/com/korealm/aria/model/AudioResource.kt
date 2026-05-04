package com.korealm.aria.model

import org.jetbrains.compose.resources.DrawableResource

data class AudioResource(
    val id: Int,
    val icon: DrawableResource,
    val title: String,
    val audioPath: String
)