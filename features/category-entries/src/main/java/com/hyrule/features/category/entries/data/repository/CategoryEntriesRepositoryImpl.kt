package com.hyrule.features.category.entries.data.repository

import com.hyrule.features.category.entries.data.source.local.LocalCategoryEntriesDataSource
import com.hyrule.features.category.entries.data.source.remote.RemoteCategoryEntriesDataSource
import com.hyrule.features.category.entries.domain.repository.CategoryEntriesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CategoryEntriesRepositoryImpl(
    private val remoteDataSource: RemoteCategoryEntriesDataSource,
    private val localDataSource: LocalCategoryEntriesDataSource,
    private val dispatcher: CoroutineDispatcher
) : CategoryEntriesRepository {

    override suspend fun getEntries(categoryName: String) = withContext(dispatcher) {
        val remote = getRemoteEntries(categoryName)

        if (remote.isSuccess) {
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
