package com.hyrule.features.categories.presentation

import com.hyrule.features.categories.domain.entity.CompendiumCategory
import com.hyrule.features.categories.domain.usecase.GetCompendiumCategoriesUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CompendiumCategoriesViewModelTest : ViewModelTestTemplate<CompendiumCategoriesState>() {

    private val useCase = mockk<GetCompendiumCategoriesUseCase>()
    private lateinit var viewModel: CompendiumCategoriesViewModel

    private val dummyList = listOf(CompendiumCategory("Food"))

    @Before
    fun setUp() {
        setStateSource { viewModel.state }
    }

    @Test
    fun `it should update state with categories`() {
        given { every { useCase() } returns dummyList }

        `when` { createViewModel() }

        assertStateSequence(
            CompendiumCategoriesState(dummyList)
        )
    }

    private fun createViewModel() {
        viewModel = CompendiumCategoriesViewModel(useCase)
    }
}
