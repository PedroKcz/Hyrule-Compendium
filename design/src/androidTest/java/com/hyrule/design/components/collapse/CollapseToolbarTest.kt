package com.hyrule.design.components.collapse

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hyrule.design.tokens.size.Size
import com.hyrule.testing.integration.ComposeUITest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CollapseToolbarTest : ComposeUITest<CollapseToolbarRobot, CollapseToolbarRobotAssertion>(
    CollapseToolbarRobot(), CollapseToolbarRobotAssertion()
) {

    @Before
    fun setup() {
        composeTestRule.setContent { CollapseTestScreen() }
    }

    @Test
    fun itShouldShouldCollapseToolbarAndReturnOnTop() {
        verifyThat {
            assertToolbarVisible()
        } andWithRobot {
            swipeToTheBottom()
        } andVerifyThat {
            assertToolbarNotVisible()
        } andWithRobot {
            swipeToTheTop()
        } andVerifyThat {
            assertToolbarVisible()
        }
    }

    @Suppress("TestFunctionName")
    @Composable
    private fun CollapseTestScreen() {
        CollapseToolbar(collapseAmount = 100.dp) { percentage ->
            Surface(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .collapsable()
            ) {
                Text(text = "Collapsable")
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .expandable(),
            ) {
                items((1..70).toList()) {
                    Text(
                        text = it.toString(),
                        modifier = Modifier.height(Size.giant)
                    )
                }
            }

            Text(
                text = "${percentage.times(100)}%",
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}
