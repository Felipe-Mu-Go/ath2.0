package com.armatuhandroll.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF5B2BE0),
    onPrimary = Color.White,
    background = Color(0xFFF7F6FB),
    surface = Color.White,
    secondaryContainer = Color(0xFFFFE7E0),
    primaryContainer = Color(0xFFE6DDFF)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFB7A4FF),
    onPrimary = Color(0xFF2C0A89),
    background = Color(0xFF16141E),
    surface = Color(0xFF211F2B),
    secondaryContainer = Color(0xFF3A2B2A),
    primaryContainer = Color(0xFF2F2456)
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
