package com.korealm.aria.ui.components.misc

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.korealm.aria.shared.Target
import com.korealm.aria.shared.getTargetPlatform
import com.korealm.aria.state.DeviceSizeCategory
import com.korealm.aria.state.LocalDeviceSizeCategory

@Composable
fun CustomDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    children: @Composable () -> Unit
) {
    val dialogWidth = when(LocalDeviceSizeCategory.current) {
        DeviceSizeCategory.Mobile -> if (getTargetPlatform() == Target.ANDROID) 350.dp else 400.dp
        DeviceSizeCategory.CompactDesktop -> 400.dp
        DeviceSizeCategory.FullDesktop -> 450.dp
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface (
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .size(dialogWidth, 550.dp)
        ) {
            children()
        }
    }
}