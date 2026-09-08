package com.korealm.aria.shared

import com.korealm.aria.utils.AndroidUrlOpener
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual fun openUrl(url: String) {
    val opener = object : KoinComponent {
        val androidUrlOpener: AndroidUrlOpener by inject()
    }.androidUrlOpener

    opener.open(url)
}