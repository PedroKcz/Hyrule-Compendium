package com.hyrule.di

import com.hyrule.features.categories.di.compendiumCategoriesModule
import org.koin.core.KoinApplication

fun KoinApplication.injectModules() = modules(
    compendiumCategoriesModule
)
