package com.hyrule.design.scene

import androidx.compose.runtime.Composable
import com.hyrule.design.scene.default.DefaultErrorScreen
import com.hyrule.design.scene.default.DefaultLoadingScreen

@Composable
fun <T> HyruleScene(
    async: Async<T>,
    loading: @Composable () -> Unit = { DefaultLoadingScreen() },
    error: @Composable (message: String?) -> Unit = { DefaultErrorScreen(it, retry) },
    retry: () -> Unit = {},
    content: @Composable (T) -> Unit,
) {
    when (async) {
        is Async.Error -> error(async.message)
        Async.Loading -> loading()
        is Async.Success -> content(async.value)
    }
}
