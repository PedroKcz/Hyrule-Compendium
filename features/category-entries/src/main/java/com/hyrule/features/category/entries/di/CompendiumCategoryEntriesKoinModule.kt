package com.hyrule.features.category.entries.di

import androidx.room.Room
import com.hyrule.features.category.entries.data.repository.CategoryEntriesRepositoryImpl
import com.hyrule.features.category.entries.data.source.local.EntryDatabase
import com.hyrule.features.category.entries.data.source.local.LocalCategoryEntriesDataSource
import com.hyrule.features.category.entries.data.source.remote.RemoteCategoryEntriesDataSource
import com.hyrule.features.category.entries.domain.repository.CategoryEntriesRepository
import com.hyrule.features.category.entries.domain.usecase.GetCategoryEntriesUseCase
import com.hyrule.features.category.entries.presentation.CompendiumCategoryEntriesViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val compendiumCategoryEntriesModule = module {
    dataModule()
    domainModule()
    presentationModule()
}

fun Module.dataModule() {
    factory { LocalCategoryEntriesDataSource(get()) }
    factory { RemoteCategoryEntriesDataSource(get()) }
    factory<CategoryEntriesRepository> {
        CategoryEntriesRepositoryImpl(get(), get(), Dispatchers.IO)
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            EntryDatabase::class.java, "entries"
        ).build().entryDao()
    }
}

fun Module.domainModule() {
    factory { GetCategoryEntriesUseCase(get()) }
}

fun Module.presentationModule() {
    viewModel { (categoryName: String) -> CompendiumCategoryEntriesViewModel(categoryName, get()) }
}
