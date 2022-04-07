package com.hyrule.features.categories.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class CoroutinesTestRule(
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {

    val scope = TestScope(dispatcher)

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}

@ExperimentalCoroutinesApi
open class ViewModelTestTemplate<T> {

    @get:Rule
    val rule = CoroutinesTestRule()

    private lateinit var stateSource: () -> StateFlow<T>

    private var given: () -> Unit = {}
    private var `when`: () -> Unit = {}

    fun setStateSource(state: () -> StateFlow<T>) {
        stateSource = state
    }

    fun runTest(test: TestScope.() -> Unit) {
        rule.scope.test()
    }

    fun given(given: TestScope.() -> Unit) {
        this.given = { rule.scope.given() }
    }

    fun `when`(`when`: TestScope.() -> Unit) {
        this.`when` = { rule.scope.`when`() }
    }

    fun assertStateSequence(vararg states: T) = rule.scope.runTest {
        given()

        `when`()

        val results = mutableListOf<T>()

        val job = launch { stateSource().toList(results) }

        job.cancel()

        assertEquals(
            "States emitted and tested are of different sizes",
            states.size,
            results.size
        )

        results.mapIndexed { index, value ->
            assertEquals("Different state at index: $index", states[index], value)
        }
    }
}
