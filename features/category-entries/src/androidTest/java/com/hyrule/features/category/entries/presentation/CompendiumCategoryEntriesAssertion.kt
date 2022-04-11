package com.hyrule.features.category.entries.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.hyrule.testing.integration.RobotAssertion

class CompendiumCategoryEntriesAssertion : RobotAssertion() {

    fun assertEntryVisible() {
        testRule.onNodeWithText("Master Sword").assertIsDisplayed()
    }

    fun assertErrorVisible() {
        testRule.onNodeWithText("error_test").assertIsDisplayed()
    }
}
