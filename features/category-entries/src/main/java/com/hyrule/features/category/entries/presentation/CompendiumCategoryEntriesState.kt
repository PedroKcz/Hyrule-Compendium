package com.hyrule.features.category.entries.presentation

import com.hyrule.design.scene.Async
import com.hyrule.features.category.entries.domain.entity.Entry

data class CompendiumCategoryEntriesState(
    val categoryName: String,
    val bannerUrl: String,
    val entries: Async<List<Entry>>
)
