package com.hyrule.design.scene

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hyrule.design.scene.default.DefaultLoadingScreen
import com.hyrule.testing.integration.ComposeUITest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HyruleSceneTest : ComposeUITest<HyruleSceneRobot, HyruleSceneRobotAssertion>(
    HyruleSceneRobot(), HyruleSceneRobotAssertion()
) {

    @Test
    fun itShouldShowLoading() {
        composeTestRule.setContent { HyruleSceneTestScreen(Async.Loading) }

        verifyThat {
            assertLoadingVisible()
        }
    }

    @Test
    fun itShouldShowError() {
        composeTestRule.setContent { HyruleSceneTestScreen(Async.Error("error")) }

        verifyThat {
            assertErrorVisible()
        }
    }

    @Test
    fun itShouldShowContent() {
        composeTestRule.setContent { HyruleSceneTestScreen(Async.Success(Unit)) }

        verifyThat {
            assertContentVisible()
        }
    }

    @Suppress("TestFunctionName")
    @Composable
    private fun HyruleSceneTestScreen(state: Async<Unit>) {
        HyruleScene(
            async = state,
            loading = { DefaultLoadingScreen(modifier = Modifier.testTag("loading")) },
            retry = {}
        ) { Text(text = "hi") }
    }
}
