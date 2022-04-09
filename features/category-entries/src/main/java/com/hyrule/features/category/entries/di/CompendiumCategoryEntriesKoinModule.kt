package com.hyrule.features.category.entries.di

import com.hyrule.features.category.entries.data.repository.CategoryEntriesRepositoryImpl
import com.hyrule.features.category.entries.data.source.local.LocalCategoryEntriesDataSource
import com.hyrule.features.category.entries.data.source.remote.RemoteCategoryEntriesDataSource
import com.hyrule.features.category.entries.domain.repository.CategoryEntriesRepository
import com.hyrule.features.category.entries.domain.usecase.GetCategoryEntriesUseCase
import com.hyrule.features.category.entries.presentation.CompendiumCategoryEntriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val compendiumCategoryEntriesModule = module {
    dataModule()
    domainModule()
    presentationModule()
}

fun Module.dataModule() {
    factory { LocalCategoryEntriesDataSource() }
    factory { RemoteCategoryEntriesDataSource() }
    factory<CategoryEntriesRepository> { CategoryEntriesRepositoryImpl(get(), get()) }
}

fun Module.domainModule() {
    factory { GetCategoryEntriesUseCase(get()) }
}

fun Module.presentationModule() {
    viewModel { (categoryName: String) -> CompendiumCategoryEntriesViewModel(categoryName, get()) }
}
