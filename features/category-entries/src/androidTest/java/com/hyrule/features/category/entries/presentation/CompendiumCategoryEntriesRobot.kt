package com.hyrule.features.category.entries.presentation

import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hyrule.testing.integration.Robot

class CompendiumCategoryEntriesRobot : Robot() {

    fun clickOnTryAgain() {
        testRule.onNodeWithText("Try again").performClick()
    }
}
