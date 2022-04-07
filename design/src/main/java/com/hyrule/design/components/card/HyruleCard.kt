package com.hyrule.design.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HyruleCard(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.onSurface,
    border: BorderStroke? = null,
    elevation: Dp = 2.dp,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val clickModifier = onClick?.let { Modifier.clickable(onClick = onClick) } ?: Modifier
    Surface(
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
        border = border,
        modifier = modifier
            .shadow(elevation = elevation, shape = shape, clip = true)
            .then(clickModifier)
    ) { content() }
}
