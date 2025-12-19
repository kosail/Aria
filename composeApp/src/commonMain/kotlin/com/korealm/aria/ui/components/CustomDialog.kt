package com.korealm.aria.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.korealm.aria.state.DeviceSizeCategory
import com.korealm.aria.state.LocalDeviceSizeCategory

@Composable
fun CustomDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    children: @Composable () -> Unit
) {
    val dialogSize = when(LocalDeviceSizeCategory.current) {
        DeviceSizeCategory.Mobile -> Pair(350.dp, 550.dp)
        DeviceSizeCategory.CompactDesktop -> Pair(450.dp, 500.dp)
        DeviceSizeCategory.FullDesktop -> Pair(650.dp, 600.dp)
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface (
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .size(dialogSize.first, dialogSize.second)
        ) {
            children()
        }
    }
}