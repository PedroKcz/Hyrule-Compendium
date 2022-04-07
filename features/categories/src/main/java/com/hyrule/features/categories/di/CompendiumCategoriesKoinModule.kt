package com.hyrule.features.categories.di

import com.hyrule.features.categories.data.repository.CompendiumCategoriesRepositoryImpl
import com.hyrule.features.categories.data.source.LocalCompendiumCategoriesSource
import com.hyrule.features.categories.domain.repository.CompendiumCategoriesRepository
import com.hyrule.features.categories.domain.usecase.GetCompendiumCategoriesUseCase
import com.hyrule.features.categories.presentation.CompendiumCategoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val compendiumCategoriesModule = module {
    dataModule()
    domainModule()
    presentationModule()
}

private fun Module.dataModule() {
    factory { LocalCompendiumCategoriesSource() }
    factory<CompendiumCategoriesRepository> { CompendiumCategoriesRepositoryImpl(get()) }
}

private fun Module.domainModule() {
    factory { GetCompendiumCategoriesUseCase(get()) }
}

private fun Module.presentationModule() {
    viewModel { CompendiumCategoriesViewModel(get()) }
}
