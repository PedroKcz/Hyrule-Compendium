package com.hyrule.features.category.entries.data.source.local

import com.hyrule.features.category.entries.domain.EntryFixtures.dummyEntries
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
class LocalCategoryEntriesDataSourceTest {

    private val entryDao = mockk<EntryDao>(relaxed = true)
    private val localSource = LocalCategoryEntriesDataSource(entryDao)

    private val dummyLocalEntries = listOf(
        LocalEntry("Master Sword", "", "equipment", listOf("a", "b")),
        LocalEntry("Bow Of Light", "a", "equipment", listOf("a")),
        LocalEntry("Sand Seal", "b", "equipment", listOf()),
    )

    @Test
    fun `it should return transformed entries`() = runTest(UnconfinedTestDispatcher()) {
        coEvery { entryDao.getByCategory(any()) } returns dummyLocalEntries

        assertEquals(dummyEntries, localSource.getEntries("equipment"))
    }

    @Test
    fun `it should set transformed entries to local`() = runTest(UnconfinedTestDispatcher()) {
        localSource.setEntries("equipment", dummyEntries)

        coVerify(atMost = 1) { entryDao.insertEntries(dummyLocalEntries) }
    }
}
