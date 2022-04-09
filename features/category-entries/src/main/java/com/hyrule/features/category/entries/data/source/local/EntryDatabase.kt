package com.hyrule.features.category.entries.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [LocalEntry::class], version = 1)
@TypeConverters(ListOfStringConverter::class)
abstract class EntryDatabase : RoomDatabase() {
    abstract fun entryDao(): EntryDao
}

class ListOfStringConverter {
    @TypeConverter
    fun fromString(listAsString: String): List<String> {
        return listAsString.split(",separator,").map { it }
    }

    @TypeConverter
    fun toString(list: List<String>): String {
        return list.joinToString(separator = ",separator,")
    }
}
