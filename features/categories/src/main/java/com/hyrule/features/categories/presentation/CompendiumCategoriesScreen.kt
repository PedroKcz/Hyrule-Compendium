package com.hyrule.features.categories.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hyrule.design.tokens.spacing.Spacing
import com.hyrule.features.categories.R

@Composable
fun CompendiumCategoriesScreen() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Spacing.medium)
    ) {
        WelcomeToCompendium()

        GoldenRupee(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun WelcomeToCompendium() {
    Text(
        text = stringResource(R.string.welcome_to),
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center
    )

    Text(
        text = stringResource(R.string.hyrule_compendium),
        style = MaterialTheme.typography.h2,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun GoldenRupee(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.golden_rupee)
    )

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultCatalogPreview() {
    CompendiumCategoriesScreen()
}
