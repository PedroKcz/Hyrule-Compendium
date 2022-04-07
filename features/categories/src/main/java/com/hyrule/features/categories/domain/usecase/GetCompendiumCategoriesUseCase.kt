package com.hyrule.features.categories.domain.usecase

import com.hyrule.features.categories.domain.repository.CompendiumCategoriesRepository

class GetCompendiumCategoriesUseCase(
    private val repository: CompendiumCategoriesRepository
) {
    operator fun invoke() = repository.getCategories()
}
