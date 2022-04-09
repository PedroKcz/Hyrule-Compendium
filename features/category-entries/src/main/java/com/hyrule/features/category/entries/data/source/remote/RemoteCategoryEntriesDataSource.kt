package com.hyrule.features.category.entries.data.source.remote

import com.hyrule.features.category.entries.domain.entity.Entry
import com.hyrule.network.HyruleKtorClient
import io.ktor.client.request.get

private const val CREATURES_SUB_CATEGORY = "creatures"

class RemoteCategoryEntriesDataSource(
    private val client: HyruleKtorClient
) {

    suspend fun getEntries(categoryName: String): List<Entry> {
        val category = categoryName.lowercase()
        return if (category == CREATURES_SUB_CATEGORY) {
            callCreaturesSpecificRequest()
        } else {
            callCategoriesApi(categoryName)
        }
    }

    private suspend fun callCategoriesApi(categoryName: String): List<Entry> {
        return client().get<RemoteCategoryEntries>("category/${categoryName.lowercase()}")
            .data
            ?.map { it.transform() } ?: listOf()
    }

    private suspend fun callCreaturesSpecificRequest(): List<Entry> {
        return client().get<RemoteCreaturesEntries>("category/$CREATURES_SUB_CATEGORY")
            .data
            .let { (it.foodData ?: listOf()).plus(it.nonFoodData ?: listOf()) }
            .map { it.transform() }
    }
}
