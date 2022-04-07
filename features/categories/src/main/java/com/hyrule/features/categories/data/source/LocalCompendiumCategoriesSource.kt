package com.hyrule.features.categories.data.source

import com.hyrule.features.categories.domain.entity.CompendiumCategory

class LocalCompendiumCategoriesSource {

    fun getCategories() = listOf(
        CompendiumCategory(name = "Creatures"),
        CompendiumCategory(name = "Equipment"),
        CompendiumCategory(name = "Materials"),
        CompendiumCategory(name = "Monsters"),
        CompendiumCategory(name = "Treasure"),
    )
}
