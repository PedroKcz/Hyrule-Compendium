package com.hyrule.design.components.collapse

import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.compose.ui.test.swipeUp
import com.hyrule.testing.integration.Robot

class CollapseToolbarRobot : Robot() {

    fun swipeToTheBottom() {
        testRule.onRoot().performTouchInput {
            swipeUp()
        }
    }

    fun swipeToTheTop() {
        testRule.onRoot().performTouchInput {
            swipeDown()
            swipeDown()
        }
    }
}
