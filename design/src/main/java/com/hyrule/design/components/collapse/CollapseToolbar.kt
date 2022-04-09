package com.hyrule.design.components.collapse

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import kotlin.math.abs

@Composable
fun CollapseToolbar(
    collapseAmount: Dp,
    modifier: Modifier = Modifier,
    content: @Composable CollapseScope.(collapsePercentage: Float) -> Unit
) {
    val collapseAmountPx = with(LocalDensity.current) { collapseAmount.roundToPx().toFloat() }
    val (offsetHeightPx, nestedScrollConnection) = getToolbarHeight(collapseAmountPx)
    val collapsePercentage =
        runCatching { abs(offsetHeightPx.value.div(collapseAmountPx)) }.getOrDefault(0f)

    Box(
        modifier = modifier.nestedScroll(nestedScrollConnection),
    ) {
        CollapseScopeInstance(
            offsetHeightPx = offsetHeightPx,
            collapseAmountPx = collapseAmountPx,
            boxScope = this
        ).content(collapsePercentage)
    }
}

@Composable
private fun getToolbarHeight(
    collapseAmountPx: Float
): Pair<MutableState<Float>, NestedScrollConnection> {
    val offsetHeightPx = remember { mutableStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val previous = offsetHeightPx.value
                updateOffsetHeight(delta)
                return Offset(x = 0f, y = -(previous - offsetHeightPx.value))
            }

            private fun updateOffsetHeight(delta: Float) {
                val newOffset = offsetHeightPx.value + delta
                offsetHeightPx.value = newOffset.coerceIn(-collapseAmountPx, 0f)
            }
        }
    }

    return Pair(offsetHeightPx, nestedScrollConnection)
}
