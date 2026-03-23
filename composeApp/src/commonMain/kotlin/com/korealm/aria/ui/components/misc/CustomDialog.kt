package com.korealm.aria.ui.components.misc

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.close
import aria.composeapp.generated.resources.close_menu
import com.korealm.aria.shared.Target
import com.korealm.aria.shared.getTargetPlatform
import com.korealm.aria.state.DeviceSizeCategory.*
import com.korealm.aria.state.LocalDeviceSizeCategory
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CustomDialog(
    onDismissRequest: () -> Unit,
    showNavbar: Boolean = false,
    modifier: Modifier = Modifier,
    children: @Composable () -> Unit
) {
    val dialogWidth = when(LocalDeviceSizeCategory.current) {
        Mobile -> if (getTargetPlatform() == Target.ANDROID) 350.dp else 400.dp
        CompactDesktop -> 400.dp
        FullDesktop -> 450.dp
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface (
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .size(dialogWidth, 600.dp)
        ) {
            if (showNavbar) {
                if (getTargetPlatform() != Target.ANDROID) {
                    Box(
                        contentAlignment = Alignment.TopEnd,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .height(56.dp)
                                .width(56.dp)
                                .clip(RoundedCornerShape(10))
                                .clickable { onDismissRequest() }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.close),
                                contentDescription = stringResource(Res.string.close_menu),
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }
            }

            children()
        }
    }
}