package com.transporte.frontend.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFF03DAC5),
    secondary = androidx.compose.ui.graphics.Color(0xFF018786),
    background = androidx.compose.ui.graphics.Color(0xFF121212)
)

@Composable
fun TransporteAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}
