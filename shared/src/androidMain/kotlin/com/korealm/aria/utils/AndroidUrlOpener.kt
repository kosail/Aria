package com.korealm.aria.utils

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

class AndroidUrlOpener(
    private val context: Context
) {
    fun open(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri()).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        context.startActivity(intent)
    }
}