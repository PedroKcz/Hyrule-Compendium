package com.hyrule.features.category.entries.presentation

import com.hyrule.design.scene.Async
import com.hyrule.features.category.entries.presentation.model.EntryModel

data class CompendiumCategoryEntriesState(
    val categoryName: String,
    val bannerUrl: String,
    val entries: Async<List<EntryModel>>
)
