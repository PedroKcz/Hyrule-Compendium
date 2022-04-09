package com.hyrule.features.category.entries.data.source.remote

import com.hyrule.features.category.entries.domain.entity.Entry
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCategoryEntries(
    @SerialName("data") val data: List<RemoteEntry>? = listOf()
)

@Serializable
data class RemoteCreaturesEntries(
    @SerialName("data") val data: RemoteCreaturesSubCategories
)

@Serializable
data class RemoteCreaturesSubCategories(
    @SerialName("food") val foodData: List<RemoteEntry>? = listOf(),
    @SerialName("non_food") val nonFoodData: List<RemoteEntry>? = listOf(),
)

@Serializable
data class RemoteEntry(
    @SerialName("name") val name: String = "",
    @SerialName("image") val image: String = "",
    @SerialName("common_locations") val locations: List<String>? = listOf()
)

fun RemoteEntry.transform() = Entry(
    name = name,
    image = image,
    locations = locations ?: listOf()
)
