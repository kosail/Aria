package com.korealm.aria.ui.components.misc

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
    val dialogSize = when(LocalDeviceSizeCategory.current) {
        DeviceSizeCategory.Mobile -> if (getTargetPlatform() == Target.ANDROID) Pair(350.dp, 550.dp) else Pair(450.dp, 550.dp)
        DeviceSizeCategory.CompactDesktop -> Pair(450.dp, 550.dp)
        DeviceSizeCategory.FullDesktop -> Pair(600.dp, 560.dp)
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
                .verticalScroll(rememberScrollState())
        ) {
            children()
        }
    }
}