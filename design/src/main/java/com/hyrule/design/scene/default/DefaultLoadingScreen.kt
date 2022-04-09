package com.hyrule.design.scene.default

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hyrule.design.R

@Composable
fun DefaultLoadingScreen() {
    val triangle by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.triangle_loading)
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(MaterialTheme.colors.background)
    ) {
        TriangleLoading(triangle)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TriangleLoading(triangle)
            TriangleLoading(triangle)
        }
    }
}

@Composable
private fun TriangleLoading(composition: LottieComposition?) {
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}
