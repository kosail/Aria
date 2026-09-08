package com.korealm.aria.state

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

// This categorizes the 3 levels of scaling that I'm aiming for
enum class DeviceSizeCategory {
    Mobile,
    CompactDesktop,
    FullDesktop
}

// This creates a slot in Compose for my observable value (DeviceSizeCategory).
// Without this value, it will fail and that's why I've to specify the fallback error
val LocalDeviceSizeCategory = staticCompositionLocalOf<DeviceSizeCategory> {
    error("No DeviceSizeCategory provided")
}

// This composable will receive the entire app UI as a wrapper.
// It will allow accessing LocalDeviceSizeCategory.current from everywhere inside the app
@Composable
fun DeviceSizeProvider(content: @Composable () -> Unit) {
    BoxWithConstraints {
        val maxWidthDp = maxWidth

        val category = when {
            maxWidthDp < 720.dp -> DeviceSizeCategory.Mobile
            maxWidthDp < 1440.dp -> DeviceSizeCategory.CompactDesktop
            else -> DeviceSizeCategory.FullDesktop
        }

        CompositionLocalProvider(
            LocalDeviceSizeCategory provides category
        ) {
            content()
        }
    }
}