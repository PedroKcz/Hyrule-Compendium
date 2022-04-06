package com.hyrule.design.tokens.palette

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Primary = Color(0xFF4aba91)
val PrimaryLight = Color(0xFF7fedc1)
val PrimaryDark = Color(0xFF008963)
val Secondary = Color(0xFFd4ce46)
val SecondaryLight = Color(0xFFffff78)
val SecondaryDark = Color(0xFFa09d05)

val DarkColorPalette = darkColors(
    primary = PrimaryLight,
    primaryVariant = PrimaryDark,
    secondary = SecondaryLight,
    secondaryVariant = SecondaryDark,
)

val LightColorPalette = lightColors(
    primary = Primary,
    primaryVariant = PrimaryDark,
    secondary = Secondary,
    secondaryVariant = SecondaryDark,
)
