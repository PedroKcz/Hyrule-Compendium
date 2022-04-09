package com.hyrule.design.components.collapse

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@LayoutScopeMarker
@Immutable
interface CollapseScope : BoxScope {

    @Stable
    fun Modifier.collapsable(): Modifier

    @Stable
    fun Modifier.expandable(): Modifier
}

internal class CollapseScopeInstance(
    private val offsetHeightPx: MutableState<Float>,
    private val collapseAmountPx: Float,
    private val boxScope: BoxScope,
) : CollapseScope {

    @Stable
    override fun Modifier.collapsable() = graphicsLayer {
        translationY = offsetHeightPx.value
    }

    @Stable
    override fun Modifier.expandable() = graphicsLayer {
        translationY = collapseAmountPx + offsetHeightPx.value
    }

    override fun Modifier.align(alignment: Alignment) = with(boxScope) {
        this@align.align(alignment)
    }

    override fun Modifier.matchParentSize() = with(boxScope) {
        this@matchParentSize.matchParentSize()
    }
}
