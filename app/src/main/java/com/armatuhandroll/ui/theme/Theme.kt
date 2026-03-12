package com.armatuhandroll.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = SushiRed,
    onPrimary = WarmIvory,
    secondary = Terracotta,
    onSecondary = WarmIvory,
    tertiary = GoldSoft,
    background = WarmIvory,
    onBackground = TextPrimary,
    surface = CreamSurface,
    onSurface = TextPrimary,
    surfaceVariant = BeigeSoft,
    onSurfaceVariant = TextSecondary,
    primaryContainer = ColorTokens.primaryContainer,
    secondaryContainer = ColorTokens.secondaryContainer,
    outline = ColorTokens.outline,
    scrim = ColorTokens.scrim
)

private val DarkColors = darkColorScheme(
    primary = ColorTokens.darkPrimary,
    onPrimary = Charcoal900,
    secondary = ColorTokens.darkSecondary,
    onSecondary = Charcoal900,
    tertiary = GoldSoft,
    background = Charcoal900,
    onBackground = WarmIvory,
    surface = Charcoal700,
    onSurface = WarmIvory,
    surfaceVariant = ColorTokens.darkSurfaceVariant,
    onSurfaceVariant = ColorTokens.darkOnSurfaceVariant,
    primaryContainer = ColorTokens.darkPrimaryContainer,
    secondaryContainer = ColorTokens.darkSecondaryContainer,
    outline = ColorTokens.darkOutline,
    scrim = ColorTokens.scrim
)

object ColorTokens {
    val primaryContainer = SushiRed.copy(alpha = 0.16f)
    val secondaryContainer = Terracotta.copy(alpha = 0.20f)
    val outline = TextSecondary.copy(alpha = 0.35f)
    val scrim = Charcoal900

    val darkPrimary = ColorTokensLightBridge.warmCoral
    val darkSecondary = ColorTokensLightBridge.sandStone
    val darkSurfaceVariant = Charcoal700.copy(alpha = 0.85f)
    val darkOnSurfaceVariant = WarmIvory.copy(alpha = 0.82f)
    val darkPrimaryContainer = ColorTokensLightBridge.warmCoral.copy(alpha = 0.25f)
    val darkSecondaryContainer = ColorTokensLightBridge.sandStone.copy(alpha = 0.23f)
    val darkOutline = WarmIvory.copy(alpha = 0.25f)
}

private object ColorTokensLightBridge {
    val warmCoral = SushiRed.copy(alpha = 0.88f)
    val sandStone = BeigeSoft.copy(alpha = 0.92f)
}

@Composable
fun ArmaTuHandrollTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
