package com.hyrule.features.category.entries.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hyrule.design.components.card.HyruleCard
import com.hyrule.design.components.collapse.CollapseToolbar
import com.hyrule.design.scene.Async
import com.hyrule.design.scene.HyruleScene
import com.hyrule.design.theme.HyruleTheme
import com.hyrule.design.tokens.size.Size
import com.hyrule.design.tokens.spacing.Spacing
import com.hyrule.features.category.entries.R
import com.hyrule.features.category.entries.presentation.model.EntryModel
import com.hyrule.imageloader.ImageLoader
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CompendiumCategoryEntriesScreen(
    navController: NavController,
    categoryName: String,
    viewModel: CompendiumCategoryEntriesViewModel = getViewModel { parametersOf(categoryName) }
) {
    LaunchedEffect(Unit) {
        viewModel.interact(CompendiumCategoryEntriesAction.LoadData)
    }

    val state by viewModel.state.collectAsState()

    CompendiumCategoryEntries(
        state = state,
        retry = { viewModel.interact(CompendiumCategoryEntriesAction.LoadData) },
        navBack = { navController.popBackStack() }
    )
}

@Composable
private fun CompendiumCategoryEntries(
    state: CompendiumCategoryEntriesState,
    retry: () -> Unit,
    navBack: () -> Unit
) {
    val headerHeight = 140.dp
    val subHeaderHeight = 58.dp

    CollapseToolbar(
        collapseAmount = headerHeight,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) { collapsePercentage ->
        Banner(
            state = state,
            height = headerHeight,
            subHeaderHeight = subHeaderHeight,
            collapsePercentage = collapsePercentage,
            onBackButtonClick = navBack,
            modifier = Modifier.collapsable(),
        )

        HyruleScene(
            async = state.entries,
            retry = retry,
            modifier = Modifier
                .padding(top = subHeaderHeight)
                .expandable()
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(Spacing.medium),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = subHeaderHeight)
                    .fillMaxSize()
                    .expandable(),
                contentPadding = PaddingValues(Spacing.medium)
            ) {
                items(it, key = { it.name }) { CategoryEntryCard(it) }
            }
        }
    }
}

@Composable
fun Banner(
    state: CompendiumCategoryEntriesState,
    height: Dp,
    subHeaderHeight: Dp,
    collapsePercentage: Float,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box {
        Box(
            modifier = modifier
        ) {
            ImageLoader(
                url = state.bannerUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height + subHeaderHeight)
                    .alpha(1 - collapsePercentage),
                contentScale = ContentScale.Crop
            )

            CategoryName(
                collapsePercentage = collapsePercentage,
                name = state.categoryName,
                modifier = Modifier
                    .height(subHeaderHeight)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(collapsePercentage)
                    .align(Alignment.BottomCenter),
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.3f)
            )
        }

        Surface(
            shape = RoundedCornerShape(percent = 50),
            color = Color.Transparent
        ) {
            Icon(
                painter = painterResource(id = com.hyrule.design.R.drawable.ic_baseline_arrow_back),
                contentDescription = stringResource(R.string.navigate_back_content_description),
                modifier = Modifier
                    .clickable { onBackButtonClick() }
                    .padding(Spacing.medium),
                tint = resolveColorFromCollapse(collapsePercentage)
            )
        }
    }
}

@Composable
private fun resolveColorFromCollapse(collapsePercentage: Float): Color {
    return runCatching {
        lerp(
            start = Color.White,
            stop = MaterialTheme.colors.onBackground,
            fraction = collapsePercentage
        )
    }.getOrDefault(Color.Black)
}

@Composable
private fun CategoryName(
    collapsePercentage: Float,
    name: String,
    modifier: Modifier
) {
    val translationAmountPx = with(LocalDensity.current) {
        Spacing.medium.times(2).roundToPx().toFloat()
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.background,
                shape = RoundedCornerShape(
                    topStart = Spacing.medium.times(1 - collapsePercentage),
                    topEnd = Spacing.medium.times(1 - collapsePercentage)
                )
            )
    ) {
        Box(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text(
                text = name,
                maxLines = 1,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onBackground,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .graphicsLayer {
                        translationX = translationAmountPx.times(collapsePercentage)
                    }
                    .padding(horizontal = Spacing.medium)
                    .align(Alignment.CenterStart)
            )
        }
    }
}

@Composable
fun CategoryEntryCard(entry: EntryModel) {
    HyruleCard(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .height(Size.giant)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ImageLoader(
                url = entry.image,
                modifier = Modifier
                    .size(Size.giant)
                    .clip(MaterialTheme.shapes.medium)
            )

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(Spacing.medium)
            ) {
                Text(
                    text = entry.name,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = entry.locations,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview
@Composable
fun CompendiumCategoryEntriesScreenPreview() {
    HyruleTheme(darkTheme = true) {
        val entryPreview = EntryModel("Master Sword", "", "Hyrule Field")

        CompendiumCategoryEntries(
            state = CompendiumCategoryEntriesState(
                categoryName = "Equipment",
                bannerUrl = "",
                entries = Async.Success(
                    listOf(
                        entryPreview,
                        entryPreview.copy(name = "bow of light")
                    )
                )
            ),
            navBack = { },
            retry = { }
        )
    }
}
