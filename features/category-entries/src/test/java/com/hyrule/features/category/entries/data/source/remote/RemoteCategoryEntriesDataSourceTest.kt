package com.hyrule.features.category.entries.data.source.remote

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
class RemoteCategoryEntriesDataSourceTest {

    private val service = mockk<RemoteCategoryEntriesService>(relaxed = true)
    private val remoteSource = RemoteCategoryEntriesDataSource(service)

    private val dummyRemoteEntries = listOf(
        RemoteEntry("Master Sword", "", listOf("a", "b")),
        RemoteEntry("Bow Of Light", "a", listOf("a")),
        RemoteEntry("Sand Seal", "b", listOf()),
    )
    private val dummyCategoryEntries = RemoteCategoryEntries(dummyRemoteEntries)
    private val dummyCreaturesSubCategories = RemoteCreaturesSubCategories(
        foodData = dummyRemoteEntries.subList(0, 1),
        nonFoodData = dummyRemoteEntries.subList(1, 3)
    )
    private val dummyCreaturesEntries = RemoteCreaturesEntries(dummyCreaturesSubCategories)

    @Test
    fun `it should return transformed entries`() = runTest(UnconfinedTestDispatcher()) {
        coEvery {
            service.getCategoryEntries("equipment")
        } returns dummyCategoryEntries

        assertEquals(dummyEntries, remoteSource.getEntries("Equipment"))
    }

    @Test
    fun `it should set transformed entries when category is creatures`() =
        runTest(UnconfinedTestDispatcher()) {
            coEvery {
                service.getCreaturesCategory()
            } returns dummyCreaturesEntries

            assertEquals(dummyEntries, remoteSource.getEntries("creatures"))
        }
}
