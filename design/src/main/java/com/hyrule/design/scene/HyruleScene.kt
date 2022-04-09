package com.hyrule.design.scene

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hyrule.design.scene.default.DefaultErrorScreen
import com.hyrule.design.scene.default.DefaultLoadingScreen

@Composable
fun <T> HyruleScene(
    async: Async<T>,
    modifier: Modifier = Modifier,
    loading: @Composable () -> Unit = { DefaultLoadingScreen(modifier) },
    error: @Composable (message: String?) -> Unit = { DefaultErrorScreen(it, retry, modifier) },
    retry: () -> Unit = {},
    content: @Composable (T) -> Unit,
) {
    when (async) {
        is Async.Error -> error(async.message)
        Async.Loading -> loading()
        is Async.Success -> content(async.value)
    }
}
