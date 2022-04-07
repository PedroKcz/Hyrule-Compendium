package com.hyrule.features.categories.presentation

import androidx.lifecycle.ViewModel
import com.hyrule.features.categories.domain.usecase.GetCompendiumCategoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CompendiumCategoriesViewModel(
    private val getCompendiumCategoriesUseCase: GetCompendiumCategoriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CompendiumCategoriesState(listOf()))
    val state: StateFlow<CompendiumCategoriesState> = _state

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        _state.update { it.copy(categories = getCompendiumCategoriesUseCase()) }
    }
}
