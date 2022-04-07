package com.hyrule.features.categories.presentation

import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hyrule.features.categories.di.compendiumCategoriesModule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

@RunWith(AndroidJUnit4::class)
class CompendiumCategoriesScreenTest : KoinTest,
    ComposeUITest<CompendiumCategoriesRobot, CompendiumCategoriesRobotAssertion>(
        CompendiumCategoriesRobot(),
        CompendiumCategoriesRobotAssertion()
    ) {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(compendiumCategoriesModule)
    }

    @Before
    fun setup() {
        composeTestRule.setContent {
            CompendiumCategoriesScreen(navController = rememberNavController())
        }
    }

    @Test
    fun itShouldShowCompendiumCategories() {
        verifyThat {
            assertCategoriesDisplayed()
        }
    }
}
