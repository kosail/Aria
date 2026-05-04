package com.korealm.aria.model

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class AudioResource(
    val id: Int,
    val icon: DrawableResource,
    val title: StringResource,
    val audioPath: String
)