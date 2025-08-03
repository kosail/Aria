package com.korealm.aria

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.korealm.aria.state.rememberAppThemeState
import com.korealm.aria.theme.WhisperingNatureTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

/* ! IMPORTANT:
 - I have to get the screen size and pixel density to create this app mobile first from the very beginning!
 - Create a ScreenDimensions State or something like that
*/

@Composable
@Preview
fun App() {
    val themeState = rememberAppThemeState()

    WhisperingNatureTheme(darkTheme = themeState.isDarkTheme) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Hello momma! This is a new project",
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(50.dp)
            )
        }
    }
}