package com.hyrule.features.categories.data

import com.hyrule.features.categories.data.repository.CompendiumCategoriesRepositoryImpl
import com.hyrule.features.categories.data.source.LocalCompendiumCategoriesSource
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CompendiumCategoriesRepositoryImplTest {

    private val repository = CompendiumCategoriesRepositoryImpl(LocalCompendiumCategoriesSource())

    @Test
    fun `it should return categories from source`() {
        Assert.assertEquals(
            LocalCompendiumCategoriesSource().getCategories(),
            repository.getCategories()
        )
    }
}
