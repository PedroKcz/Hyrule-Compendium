package com.hyrule.features.category.entries.domain.repository

import com.hyrule.features.category.entries.domain.entity.Entry

interface CategoryEntriesRepository {
    suspend fun getEntries(categoryName: String): Result<List<Entry>>
}
