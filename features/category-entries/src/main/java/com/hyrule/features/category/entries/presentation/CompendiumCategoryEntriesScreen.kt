package com.hyrule.features.category.entries.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.hyrule.design.theme.HyruleTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun CompendiumCategoryEntriesScreen(
    navController: NavController,
    viewModel: CompendiumCategoryEntriesViewModel = getViewModel()
) {

}

@Composable
private fun CompendiumCategoryEntries() {

}

@Composable
fun CompendiumCategoryEntriesScreenPreview() {
    HyruleTheme(darkTheme = true) {
        CompendiumCategoryEntries()
    }
}
