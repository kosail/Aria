package com.korealm.aria.ui.components.misc

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.korealm.aria.state.AppThemeState
import com.korealm.aria.utils.lighten

@Composable
fun CustomDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    offset: DpOffset,
    themeState: AppThemeState,
    modifier: Modifier = Modifier,
    children: @Composable () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(12.dp),
        offset = offset,
        shadowElevation = 12.dp,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f)),
        modifier = modifier
            .widthIn(min = 200.dp, max = 250.dp)
            .background(
                if (themeState.isDarkTheme) MaterialTheme.colorScheme.background.lighten(0.2f)
                else MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .padding(top = 4.dp)
        ) {
            children()
        }
    }
}