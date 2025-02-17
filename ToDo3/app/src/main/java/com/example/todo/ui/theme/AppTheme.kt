package com.example.todo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val DarkColorPalette = darkColorScheme(
    primary = Teal200,
    secondary = Teal200
)

private val LightColorPalette = lightColorScheme(
    primary = Teal200,
    secondary = Teal200
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    val Shapes = Shapes(
        small = RoundedCornerShape(4.dp),   // For small elements like buttons
        medium = RoundedCornerShape(8.dp),  // For cards, dialogs, etc.
        large = RoundedCornerShape(16.dp)   // For larger surfaces
    )

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
