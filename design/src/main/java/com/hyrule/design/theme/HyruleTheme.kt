package com.hyrule.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.hyrule.design.tokens.palette.DarkColorPalette
import com.hyrule.design.tokens.palette.LightColorPalette
import com.hyrule.design.tokens.shape.Shapes
import com.hyrule.design.tokens.typography.Typography

@Composable
fun HyruleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
