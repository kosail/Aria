package com.korealm.aria.shared

import java.awt.Desktop
import java.net.URI.create

actual fun openUrl(url: String) {
    if (!Desktop.isDesktopSupported()) return

    val desktop = Desktop.getDesktop()
    if (!desktop.isSupported(Desktop.Action.BROWSE)) return

    desktop.browse(
        create(url)
    )
}