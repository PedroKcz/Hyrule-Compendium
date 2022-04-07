package com.hyrule.navcontroller

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hyrule.features.categories.presentation.CompendiumCategoriesScreen

@Composable
fun HyruleNavController() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "categories") {
        composable("categories") { CompendiumCategoriesScreen(navController) }
    }
}
