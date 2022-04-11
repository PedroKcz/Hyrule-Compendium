package com.hyrule.features.category.entries.data.source.local

import com.hyrule.features.category.entries.domain.entity.Entry

class LocalCategoryEntriesDataSource(
    private val entryDao: EntryDao
) {

    suspend fun setEntries(categoryName: String, entries: List<Entry>) {
        entryDao.insertEntries(entries.transformToRoomEntity(categoryName))
    }

    suspend fun getEntries(categoryName: String): List<Entry> =
        entryDao.getByCategory(categoryName).transformToDomain()

    private fun List<Entry>.transformToRoomEntity(categoryName: String) = map {
        LocalEntry(
            name = it.name,
            image = it.image,
            category = categoryName,
            locations = it.locations
        )
    }

    private fun List<LocalEntry>.transformToDomain() = map {
        Entry(
            name = it.name,
            image = it.image,
            locations = it.locations
        )
    }
}
