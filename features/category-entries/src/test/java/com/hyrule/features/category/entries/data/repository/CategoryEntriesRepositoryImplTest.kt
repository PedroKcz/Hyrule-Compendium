package com.hyrule.features.category.entries.data.repository

import com.hyrule.features.category.entries.data.source.local.LocalCategoryEntriesDataSource
import com.hyrule.features.category.entries.data.source.remote.RemoteCategoryEntriesDataSource
import com.hyrule.features.category.entries.domain.EntryFixtures.dummyEntries
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
class CategoryEntriesRepositoryImplTest {

    private val remoteDataSource = mockk<RemoteCategoryEntriesDataSource>(relaxed = true)
    private val localDataSource = mockk<LocalCategoryEntriesDataSource>(relaxed = true)
    private val repository = CategoryEntriesRepositoryImpl(
        remoteDataSource, localDataSource, UnconfinedTestDispatcher()
    )

    @Test
    fun `it should return entries from remote on success`() = runTest(UnconfinedTestDispatcher()) {
        coEvery { remoteDataSource.getEntries(any()) } returns dummyEntries

        assertEquals(Result.success(dummyEntries), repository.getEntries("equipment"))
    }
}
