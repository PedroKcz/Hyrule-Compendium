package com.hyrule.features.categories.domain.repository

import com.hyrule.features.categories.domain.entity.CompendiumCategory

interface CompendiumCategoriesRepository {
    fun getCategories(): List<CompendiumCategory>
}
