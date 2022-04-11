package com.hyrule.design.components.collapse

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.hyrule.testing.integration.RobotAssertion

class CollapseToolbarRobotAssertion : RobotAssertion() {

    fun assertToolbarVisible() {
        testRule.onNodeWithText("Collapsable").assertIsDisplayed()
        testRule.onNodeWithText("1").assertIsDisplayed()
        testRule.onNodeWithText("0.0%").assertIsDisplayed()
    }

    fun assertToolbarNotVisible() {
        testRule.onNodeWithText("100.0%").assertIsDisplayed()
        testRule.onNodeWithText("Collapsable").assertIsNotDisplayed()
    }
}
