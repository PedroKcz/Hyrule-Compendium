package com.hyrule.features.category.entries.data.repository

import com.hyrule.features.category.entries.data.source.local.LocalCategoryEntriesDataSource
import com.hyrule.features.category.entries.data.source.remote.RemoteCategoryEntriesDataSource
import com.hyrule.features.category.entries.domain.entity.Entry
import com.hyrule.features.category.entries.domain.repository.CategoryEntriesRepository

class CategoryEntriesRepositoryImpl(
    private val remoteDataSource: RemoteCategoryEntriesDataSource,
    private val localDataSource: LocalCategoryEntriesDataSource,
) : CategoryEntriesRepository {

    override suspend fun getEntries(categoryName: String): Result<List<Entry>> {
        val remote = getRemoteEntries(categoryName)

        return if (remote.isSuccess) {
            remote
        } else {
            val local = getLocalEntries(categoryName)
            if (local.isEmpty()) remote else Result.success(local)
        }
    }

    private suspend fun getRemoteEntries(categoryName: String) = runCatching {
        remoteDataSource.getEntries(categoryName).also {
            localDataSource.setEntries(categoryName, it)
        }
    }

    private suspend fun getLocalEntries(categoryName: String) = runCatching {
        localDataSource.getEntries(categoryName = categoryName)
    }.getOrDefault(listOf())
}
