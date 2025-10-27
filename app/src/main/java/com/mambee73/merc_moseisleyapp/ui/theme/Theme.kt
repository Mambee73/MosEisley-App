package com.mambee73.merc_moseisleyapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


val TatooineSand = Color(0xFFD2B48C)
val RustMetal = Color(0xFF8B5E3C)
val CantinaGlow = Color(0xFFFFD700)
val BlasterGray = Color(0xFF4B4B4B)
val NeonCyan = Color(0xFF00FFFF)
val DarkSky = Color(0xFF1A1A1A)

private val DarkColorScheme = darkColorScheme(
    primary = CantinaGlow,
    secondary = NeonCyan,
    tertiary = RustMetal,
    background = DarkSky,
    surface = DarkSky,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = RustMetal,
    secondary = NeonCyan,
    tertiary = CantinaGlow,
    background = TatooineSand,
    surface = TatooineSand,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.Black,
    onBackground = BlasterGray,
    onSurface = BlasterGray
)

@Composable
fun MercMosEisleyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Desactivado para mantener tu paleta personalizada
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
