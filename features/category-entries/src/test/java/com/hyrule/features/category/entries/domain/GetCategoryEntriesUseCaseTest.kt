package com.hyrule.features.category.entries.domain

import com.hyrule.features.category.entries.domain.EntryFixtures.dummyEntries
import com.hyrule.features.category.entries.domain.repository.CategoryEntriesRepository
import com.hyrule.features.category.entries.domain.usecase.GetCategoryEntriesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetCategoryEntriesUseCaseTest {

    private val repository = mockk<CategoryEntriesRepository>()
    private val useCase = GetCategoryEntriesUseCase(repository)

    @Test
    fun `it should return entries`() = runTest(UnconfinedTestDispatcher()) {
        coEvery { repository.getEntries(any()) } returns Result.success(dummyEntries)

        assertEquals(Result.success(dummyEntries), useCase("equipment"))
    }
}
