package com.hyrule.features.categories.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hyrule.design.components.card.HyruleCard
import com.hyrule.design.theme.HyruleTheme
import com.hyrule.design.tokens.palette.Palette
import com.hyrule.design.tokens.size.Size
import com.hyrule.design.tokens.spacing.Spacing
import com.hyrule.features.categories.R
import com.hyrule.features.categories.domain.entity.CompendiumCategory
import org.koin.androidx.compose.getViewModel

@Composable
fun CompendiumCategoriesScreen(
    navController: NavController,
    viewModel: CompendiumCategoriesViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()

    CompendiumCategories(
        state = state,
        onCategoryClicked = { navController.navigate("category-entries/$it") }
    )
}

@Composable
private fun CompendiumCategories(
    state: CompendiumCategoriesState,
    onCategoryClicked: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(all = Spacing.medium)
    ) {
        WelcomeToCompendium()

        GoldenRupee(modifier = Modifier.weight(1f))

        CompendiumCategoriesGrid(state.categories, onCategoryClicked)
    }
}

@Composable
private fun WelcomeToCompendium() {
    Text(
        text = stringResource(R.string.welcome_to),
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onBackground
    )

    Text(
        text = stringResource(R.string.hyrule_compendium),
        style = MaterialTheme.typography.h2,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onBackground
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

@Composable
fun CompendiumCategoriesGrid(
    actions: List<CompendiumCategory>,
    onCategoryClicked: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        (0 until actions.size.div(2)).map {
            CompendiumCategoriesRow(
                first = actions[it.times(2)],
                second = actions[it.times(2).plus(1)],
                onCategoryClicked = onCategoryClicked
            )
        }

        if (actions.size % 2 != 0) {
            CompendiumCategoryCard(
                category = actions.last(),
                modifier = Modifier.fillMaxWidth(),
                onCategoryClicked = onCategoryClicked
            )
        }
    }
}

@Composable
private fun CompendiumCategoriesRow(
    first: CompendiumCategory,
    second: CompendiumCategory,
    onCategoryClicked: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Spacing.medium),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        val cardModifier = Modifier.weight(1f)

        CompendiumCategoryCard(first, cardModifier, onCategoryClicked)

        CompendiumCategoryCard(second, cardModifier, onCategoryClicked)
    }
}

@Composable
private fun CompendiumCategoryCard(
    category: CompendiumCategory,
    modifier: Modifier = Modifier,
    onCategoryClicked: (String) -> Unit
) {
    HyruleCard(
        modifier = modifier.height(Size.medium),
        onClick = { onCategoryClicked(category.name) },
        border = BorderStroke(Size.hairline, Palette.Secondary)
    ) {
        Box {
            Text(
                text = category.name,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompendiumCategoriesScreenPreview() {
    HyruleTheme(darkTheme = true) {
        CompendiumCategories(
            state = CompendiumCategoriesState(
                categories = listOf(
                    CompendiumCategory("Creatures"),
                    CompendiumCategory("Monsters"),
                )
            ),
            onCategoryClicked = {}
        )
    }
}
