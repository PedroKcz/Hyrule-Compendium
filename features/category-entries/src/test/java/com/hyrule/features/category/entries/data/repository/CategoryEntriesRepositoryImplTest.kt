package com.hyrule.features.category.entries.data.repository

import com.hyrule.features.category.entries.data.source.local.LocalCategoryEntriesDataSource
import com.hyrule.features.category.entries.data.source.remote.RemoteCategoryEntriesDataSource
import com.hyrule.features.category.entries.domain.EntryFixtures.dummyEntries
import com.hyrule.features.category.entries.domain.entity.Entry
import io.mockk.coEvery
import io.mockk.coVerify
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
    private val dispatcher = UnconfinedTestDispatcher()

    @Test
    fun `it should return entries from remote on success`() = runTest(dispatcher) {
        coEvery { remoteDataSource.getEntries(any()) } returns dummyEntries

        assertEquals(Result.success(dummyEntries), repository.getEntries("equipment"))
        coVerify(atMost = 1) { localDataSource.setEntries("equipment", dummyEntries) }
    }

    @Test
    fun `it should return entries from local on request failure`() = runTest(dispatcher) {
        coEvery { remoteDataSource.getEntries(any()) } throws Throwable("error")
        coEvery { localDataSource.getEntries(any()) } returns dummyEntries

        assertEquals(Result.success(dummyEntries), repository.getEntries("equipment"))
        coVerify(atMost = 0, atLeast = 0) {
            localDataSource.setEntries("equipment", dummyEntries)
        }
    }

    @Test
    fun `it should return remote error if local fails or is empty`() = runTest(dispatcher) {
        val remoteError = Throwable("error")
        coEvery { remoteDataSource.getEntries(any()) } throws remoteError
        coEvery { localDataSource.getEntries(any()) } returns listOf()

        assertEquals(
            Result.failure<List<Entry>>(remoteError),
            repository.getEntries("equipment")
        )

        coEvery { localDataSource.getEntries(any()) } throws Throwable("local_error")

        assertEquals(
            Result.failure<List<Entry>>(remoteError),
            repository.getEntries("equipment")
        )
    }
}
