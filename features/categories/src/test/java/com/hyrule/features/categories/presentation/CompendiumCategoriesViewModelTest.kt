package com.hyrule.features.categories.presentation

import com.hyrule.features.categories.domain.CompendiumCategoryFixtures.dummyCategories
import com.hyrule.features.categories.domain.usecase.GetCompendiumCategoriesUseCase
import com.hyrule.testing.unit.ViewModelTestTemplate
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

    @Before
    fun setUp() {
        setStateSource { viewModel.state }
    }

    @Test
    fun `it should update state with categories`() {
        given { every { useCase() } returns dummyCategories }

        whenever { createViewModel() }

        assertStateSequence(CompendiumCategoriesState(dummyCategories))
    }

    private fun createViewModel() {
        viewModel = CompendiumCategoriesViewModel(useCase)
    }
}
