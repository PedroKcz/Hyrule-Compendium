package com.hyrule.features.categories.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.hyrule.testing.integration.RobotAssertion

class CompendiumCategoriesRobotAssertion : RobotAssertion() {

    fun assertCategoriesDisplayed() {
        assertTextDisplayed("Creatures")
        assertTextDisplayed("Equipment")
        assertTextDisplayed("Materials")
        assertTextDisplayed("Monsters")
        assertTextDisplayed("Treasure")
    }

    private fun assertTextDisplayed(text: String) {
        testRule.onNodeWithText(text).assertIsDisplayed()
    }
}
