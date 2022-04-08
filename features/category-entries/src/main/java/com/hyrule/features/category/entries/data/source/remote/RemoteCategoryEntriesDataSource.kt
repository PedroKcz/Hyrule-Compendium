package com.hyrule.features.category.entries.data.source.remote

import com.hyrule.features.category.entries.domain.entity.Entry

class RemoteCategoryEntriesDataSource {

    suspend fun getEntries(categoryName: String): List<Entry> {
        return listOf()
    }
}
