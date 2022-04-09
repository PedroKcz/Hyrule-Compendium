package com.hyrule.di

import com.hyrule.features.categories.di.compendiumCategoriesModule
import com.hyrule.features.category.entries.di.compendiumCategoryEntriesModule
import com.hyrule.network.HyruleKtorClient
import io.ktor.client.engine.cio.CIO
import org.koin.core.KoinApplication
import org.koin.dsl.module

val networkModule = module {
    single { HyruleKtorClient(CIO.create()) }
}

fun KoinApplication.injectModules() = modules(
    compendiumCategoriesModule,
    compendiumCategoryEntriesModule,
    networkModule
)
