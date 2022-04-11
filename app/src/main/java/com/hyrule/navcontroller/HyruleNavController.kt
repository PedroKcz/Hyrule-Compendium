package com.hyrule.navcontroller

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hyrule.features.categories.presentation.CompendiumCategoriesScreen
import com.hyrule.features.category.entries.presentation.CompendiumCategoryEntriesScreen

@Composable
fun HyruleNavController() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "categories") {
        composable("categories") { CompendiumCategoriesScreen(navController) }

        composable(
            route = "category-entries/{categoryName}",
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) {
            CompendiumCategoryEntriesScreen(
                navController = navController,
                categoryName = it.arguments?.getString("categoryName") ?: ""
            )
        }
    }
}
