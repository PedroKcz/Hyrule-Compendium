package com.hyrule.features.category.entries.data.source.remote

import com.hyrule.features.category.entries.domain.entity.Entry

internal const val CREATURES_SUB_CATEGORY = "creatures"

class RemoteCategoryEntriesDataSource(
    private val service: RemoteCategoryEntriesService
) {

    suspend fun getEntries(categoryName: String): List<Entry> {
        val category = categoryName.lowercase()
        return if (category == CREATURES_SUB_CATEGORY) {
            callCreaturesSpecificRequest()
        } else {
            callCategoriesApi(category)
        }
    }

    private suspend fun callCategoriesApi(categoryName: String): List<Entry> {
        return service.getCategoryEntries(categoryName)
            .data
            ?.map { it.transform() } ?: listOf()
    }

    private suspend fun callCreaturesSpecificRequest(): List<Entry> {
        return service.getCreaturesCategory()
            .data
            .let { (it.foodData ?: listOf()).plus(it.nonFoodData ?: listOf()) }
            .map { it.transform() }
    }
}
