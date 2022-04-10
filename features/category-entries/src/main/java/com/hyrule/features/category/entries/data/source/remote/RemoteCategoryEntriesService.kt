package com.hyrule.features.category.entries.data.source.remote

import com.hyrule.network.HyruleKtorClient
import io.ktor.client.request.get

class RemoteCategoryEntriesService(
    private val client: HyruleKtorClient
) {

    suspend fun getCategoryEntries(name: String) =
        client().get<RemoteCategoryEntries>("category/$name")

    suspend fun getCreaturesCategory() =
        client().get<RemoteCreaturesEntries>("category/$CREATURES_SUB_CATEGORY")
}
