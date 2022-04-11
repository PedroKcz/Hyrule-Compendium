package com.hyrule.features.category.entries.presentation

import com.hyrule.design.scene.Async
import com.hyrule.features.category.entries.domain.EntryFixtures.dummyEntries
import com.hyrule.features.category.entries.domain.usecase.GetCategoryEntriesUseCase
import com.hyrule.features.category.entries.presentation.model.EntryModel
import com.hyrule.testing.unit.ViewModelTestTemplate
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

private const val BANNER_URL = "https://images.alphacoders.com/789/thumb-1920-789452.jpg"

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CompendiumCategoryEntriesViewModelTest :
    ViewModelTestTemplate<CompendiumCategoryEntriesState>() {

    private val useCase = mockk<GetCategoryEntriesUseCase>()
    private lateinit var viewModel: CompendiumCategoryEntriesViewModel

    private val dummyCategory = "creatures"
    private val initialState = CompendiumCategoryEntriesState(
        categoryName = dummyCategory,
        bannerUrl = BANNER_URL,
        entries = Async.Loading
    )
    private val dummyEntriesModel = listOf(
        EntryModel("Master Sword", "", "a, b"),
        EntryModel("Bow Of Light", "a", "a"),
        EntryModel("Sand Seal", "b", ""),
    )

    @Before
    fun setUp() {
        createViewModel()
        setStateSource { viewModel.state }
    }

    @Test
    fun `it should update state with entries`() {
        given { coEvery { useCase(dummyCategory) } returns Result.success(dummyEntries) }

        whenever {
            viewModel.interact(CompendiumCategoryEntriesAction.LoadData)
        }

        assertStateSequence(
            initialState,
            initialState.copy(entries = Async.Success(dummyEntriesModel))
        )
    }

    @Test
    fun `it should emit error when call fails`() {
        given { coEvery { useCase(dummyCategory) } returns Result.failure(Throwable("error")) }

        whenever {
            viewModel.interact(CompendiumCategoryEntriesAction.LoadData)
        }

        assertStateSequence(
            initialState,
            initialState.copy(entries = Async.Error("error"))
        )
    }

    private fun createViewModel() {
        viewModel = CompendiumCategoryEntriesViewModel(dummyCategory, useCase)
    }
}
