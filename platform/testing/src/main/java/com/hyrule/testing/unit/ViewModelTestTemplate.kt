package com.hyrule.testing.unit

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule

@ExperimentalCoroutinesApi
open class ViewModelTestTemplate<T> {

    @get:Rule
    val rule = CoroutinesTestRule()

    private lateinit var stateSource: () -> StateFlow<T>

    private var given: () -> Unit = {}
    private var whenever: () -> Unit = {}

    fun setStateSource(state: () -> StateFlow<T>) {
        stateSource = state
    }

    fun runTest(test: TestScope.() -> Unit) {
        rule.scope.test()
    }

    fun given(given: TestScope.() -> Unit) {
        this.given = { rule.scope.given() }
    }

    fun whenever(whenever: TestScope.() -> Unit) {
        this.whenever = { rule.scope.whenever() }
    }

    fun assertStateSequence(vararg states: T) = rule.scope.runTest {
        given()

        whenever()

        val results = mutableListOf<T>()

        val job = launch { stateSource().toList(results) }

        job.cancel()

        Assert.assertEquals(
            "States emitted and tested are of different sizes",
            states.size,
            results.size
        )

        results.mapIndexed { index, value ->
            Assert.assertEquals("Different state at index: $index", states[index], value)
        }
    }
}
