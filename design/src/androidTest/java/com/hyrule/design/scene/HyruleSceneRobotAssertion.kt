package com.hyrule.design.scene

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.hyrule.testing.integration.RobotAssertion

class HyruleSceneRobotAssertion : RobotAssertion() {

    fun assertLoadingVisible() {
        testRule.onNodeWithTag("loading").assertIsDisplayed()
    }

    fun assertErrorVisible() {
        testRule.onNodeWithText("error").assertIsDisplayed()
    }

    fun assertContentVisible() {
        testRule.onNodeWithText("hi").assertIsDisplayed()
    }
}
