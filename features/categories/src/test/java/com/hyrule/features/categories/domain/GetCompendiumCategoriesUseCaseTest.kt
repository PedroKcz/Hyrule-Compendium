package com.hyrule.features.categories.domain

import com.hyrule.features.categories.domain.CompendiumCategoryFixtures.dummyCategories
import com.hyrule.features.categories.domain.repository.CompendiumCategoriesRepository
import com.hyrule.features.categories.domain.usecase.GetCompendiumCategoriesUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetCompendiumCategoriesUseCaseTest {

    private val repository = mockk<CompendiumCategoriesRepository>()
    private val useCase = GetCompendiumCategoriesUseCase(repository)

    @Test
    fun `it should return categories`() {
        every { repository.getCategories() } returns dummyCategories

        assertEquals(dummyCategories, useCase())
    }
}
