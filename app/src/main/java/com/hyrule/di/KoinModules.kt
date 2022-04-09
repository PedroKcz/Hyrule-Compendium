package com.hyrule.di

import com.hyrule.features.categories.di.compendiumCategoriesModule
import com.hyrule.features.category.entries.di.compendiumCategoryEntriesModule
import org.koin.core.KoinApplication

fun KoinApplication.injectModules() = modules(
    compendiumCategoriesModule,
    compendiumCategoryEntriesModule
)
