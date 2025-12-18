package com.korealm.aria.utils

enum class Target {
    DESKTOP, WEB, ANDROID
}

expect fun getTargetPlatform(): Target