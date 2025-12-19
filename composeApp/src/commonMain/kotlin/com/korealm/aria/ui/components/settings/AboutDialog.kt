package com.korealm.aria.ui.components.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.korealm.aria.ui.components.CustomDialog

@Composable
fun AboutDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {

    }
}