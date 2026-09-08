package com.korealm.aria.ui.components.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.korealm.aria.state.AppThemeState

@Composable
fun HomeScreenCard(
    themeState: AppThemeState,
    onLongClick: (() -> Unit)? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    var isHover by remember { mutableStateOf(false) }

    val baseColor = if (themeState.isDarkTheme) {
        MaterialTheme.colorScheme.tertiary
    } else {
        MaterialTheme.colorScheme.tertiaryContainer
    }

    val targetAlpha = if (isHover) {
        if (themeState.isDarkTheme) 0.1f else 1f
    } else {
        0f
    }

    val animatedAlpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(100)
    )

    val animatedColor = baseColor.copy(alpha = animatedAlpha)

    Surface(
        shape = RoundedCornerShape(10.dp),
        color = animatedColor,
        modifier = modifier
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while(true) {
                        val type = awaitPointerEvent().type

                        when (type) {
                            PointerEventType.Enter -> isHover = true
                            PointerEventType.Exit -> isHover = false
                        }
                    }
                }
            }
            .combinedClickable(
                onClick = onClick,
                onLongClick = { onLongClick?.invoke() }
            )
    ) {
        content()
    }
}