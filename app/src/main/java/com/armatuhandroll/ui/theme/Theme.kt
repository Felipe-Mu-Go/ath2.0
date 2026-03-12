package com.armatuhandroll.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFFB33A2F),
    onPrimary = Color(0xFFFFF8F2),
    background = Color(0xFFFFF4EA),
    surface = Color(0xFFFFFBF7),
    onSurface = Color(0xFF231815),
    onSurfaceVariant = Color(0xFF6A5854),
    secondaryContainer = Color(0xFFF6D6BE),
    primaryContainer = Color(0xFFF2C7AB),
    scrim = Color(0xFF201414)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFFFB89A),
    onPrimary = Color(0xFF5E190F),
    background = Color(0xFF18110F),
    surface = Color(0xFF251A16),
    onSurface = Color(0xFFFFECE2),
    onSurfaceVariant = Color(0xFFD8B8A9),
    secondaryContainer = Color(0xFF5E4034),
    primaryContainer = Color(0xFF5A2A23),
    scrim = Color.Black
)

@Composable
fun ArmaTuHandrollTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}
