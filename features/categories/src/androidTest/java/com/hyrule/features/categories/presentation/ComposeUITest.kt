package com.hyrule.features.categories.presentation

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule

open class Robot {
    lateinit var testRule: ComposeContentTestRule
}

open class RobotAssertion {
    lateinit var testRule: ComposeContentTestRule
}

open class ComposeUITest<R : Robot, A : RobotAssertion>(
    private val robot: R,
    private val robotAssertion: A,
    rule: ComposeContentTestRule = createComposeRule(),
) {

    @get:Rule
    val composeTestRule = rule

    init {
        robot.testRule = composeTestRule
        robotAssertion.testRule = composeTestRule
    }

    infix fun withRobot(fn: R.() -> Unit): R =
        robot.apply(fn)

    infix fun verifyThat(fn: A.() -> Unit): A =
        robotAssertion.apply(fn)

    infix fun R.verifyThat(fn: A.() -> Unit): A =
        robotAssertion.apply(fn)

    infix fun A.andThen(fn: R.() -> Unit): R =
        robot.apply(fn)
}
