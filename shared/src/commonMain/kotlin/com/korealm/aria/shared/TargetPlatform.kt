package com.korealm.aria.shared

enum class Target {
    DESKTOP, WEB, ANDROID
}

expect fun getTargetPlatform(): Target