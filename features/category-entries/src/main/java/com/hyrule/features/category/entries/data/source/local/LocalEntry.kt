package com.hyrule.features.category.entries.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalEntry(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "locations") val locations: List<String>
)
