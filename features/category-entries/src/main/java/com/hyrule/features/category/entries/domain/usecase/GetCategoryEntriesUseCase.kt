package com.hyrule.features.category.entries.domain.usecase

import com.hyrule.features.category.entries.domain.repository.CategoryEntriesRepository

class GetCategoryEntriesUseCase(
    private val repository: CategoryEntriesRepository
) {
    suspend operator fun invoke(categoryName: String) = repository.getEntries(categoryName)
}
