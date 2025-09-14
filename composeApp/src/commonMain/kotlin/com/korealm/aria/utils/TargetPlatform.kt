package com.korealm.aria.utils

enum class Target {
    DESKTOP, WEB
}

expect fun getTargetPlatform(): Target