package com.hyrule.features.category.entries.data.source.local

import com.hyrule.features.category.entries.domain.entity.Entry

class LocalCategoryEntriesDataSource {

    suspend fun setEntries(categoryName: String, entries: List<Entry>) {

    }

    suspend fun getEntries(categoryName: String): List<Entry> = listOf()
}
