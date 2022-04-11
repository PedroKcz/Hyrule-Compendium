package com.hyrule.features.category.entries.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EntryDao {
    @Query("SELECT * FROM localentry WHERE category IN (:category)")
    suspend fun getByCategory(category: String): List<LocalEntry>

    @Insert
    suspend fun insertEntries(entries: List<LocalEntry>)
}
