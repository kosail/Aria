package com.korealm.aria.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

/**
 * Copies a file from the provided URI to the application's internal storage in an "audio" directory.
 *
 * @param context The application context used to access the internal storage and content resolver.
 * @param uri The URI of the file to be copied to internal storage.
 * @return The absolute path of the new file in the internal storage.
 */
suspend fun copyToInternalStorage(
    context: Context,
    uri: Uri
): String = withContext(Dispatchers.IO) {
    val resolver = context.contentResolver

    val name = queryFileName(resolver, uri) ?: "audio_${System.currentTimeMillis()}"

    val dir = File(context.filesDir, "audio").apply { mkdirs() }
    val file = File(dir, name)

    resolver.openInputStream(uri).use { input ->
        FileOutputStream(file).use { output ->
            input?.copyTo(output)
        }
    }

    file.absolutePath
}

/**
 * Queries the display name of a file from the provided content URI using the content resolver.
 *
 * @param resolver The content resolver used to query the URI.
 * @param uri The content URI of the file to query.
 * @return The display name of the file, or null if it could not be determined.
 */
private fun queryFileName(
    resolver: ContentResolver,
    uri: Uri
): String? {
    val cursor = resolver.query(uri, null, null, null, null) ?: return null
    return cursor.use {
        val index = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (it.moveToFirst() && index >= 0) it.getString(index) else null
    }
}