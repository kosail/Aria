package com.korealm.aria.ui.components.settings.timer

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aria.composeapp.generated.resources.Res
import aria.composeapp.generated.resources.settings_timer_start
import aria.composeapp.generated.resources.timer_remaining_time
import aria.composeapp.generated.resources.timer_start
import aria.composeapp.generated.resources.timer_stop
import com.korealm.aria.state.LocalTimerState
import com.korealm.aria.ui.components.misc.CustomDialog
import com.korealm.aria.ui.components.misc.GtkButton
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    val timer = LocalTimerState.current
    var seconds by remember { mutableStateOf(13L) }

    CustomDialog(
        onDismissRequest = onDismissRequest,
        showNavbar = true,
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AnimatedContent(
                    targetState = timer.isRunning,
                    transitionSpec = {
                        fadeIn(tween(200)) togetherWith
                                fadeOut(tween(150))
                    }
                ) { isRunning ->
                    val titleRes = if (isRunning) Res.string.timer_remaining_time else Res.string.settings_timer_start
                    Text(
                        text = stringResource(titleRes).uppercase(),
                        style = MaterialTheme.typography.headlineLarge,
                        letterSpacing = 1.sp,
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp)
            )

            AnimatedContent(
                targetState = timer.isRunning,
                transitionSpec = {
                    fadeIn(tween(200)) togetherWith
                            fadeOut(tween(150))
                }
            ) { isRunning ->
                when (isRunning) {
                    true -> {
                        ShowTime(
                            timeInSeconds = timer.remainingSeconds
                        )
                    }
                    false -> {
                        SelectTime(
                            onTimeSet = { seconds -> timer.start(seconds) },
                        )
                    }
                }
            }


            Spacer(
                modifier = Modifier.weight(1f)
            )

            GtkButton(
                onClick = {
                    if (timer.isRunning) {
                        timer.reset()
                    } else {
                        timer.start(seconds)
                    }
                },
                modifier = Modifier.align(Alignment.End),
            ) {
                AnimatedContent(
                    targetState = timer.isRunning,
                    transitionSpec = {
                        fadeIn(tween(200)) togetherWith
                                fadeOut(tween(150))
                    }
                ) { isRunning ->
                    val titleRes = if (isRunning) Res.string.timer_stop else Res.string.timer_start

                    Text(
                        text = stringResource(titleRes),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}