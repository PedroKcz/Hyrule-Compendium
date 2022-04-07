package com.hyrule.features.categories.data.repository

import com.hyrule.features.categories.data.source.LocalCompendiumCategoriesSource
import com.hyrule.features.categories.domain.repository.CompendiumCategoriesRepository

class CompendiumCategoriesRepositoryImpl(
    private val localDataSource: LocalCompendiumCategoriesSource
) : CompendiumCategoriesRepository {

    override fun getCategories() = localDataSource.getCategories()
}
