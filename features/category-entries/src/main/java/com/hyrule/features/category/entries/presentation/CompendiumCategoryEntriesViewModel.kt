package com.hyrule.features.category.entries.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyrule.design.scene.Async
import com.hyrule.features.category.entries.domain.entity.Entry
import com.hyrule.features.category.entries.domain.usecase.GetCategoryEntriesUseCase
import com.hyrule.features.category.entries.presentation.model.EntryModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val BANNER_URL = "https://images.alphacoders.com/789/thumb-1920-789452.jpg"

class CompendiumCategoryEntriesViewModel(
    private val categoryName: String,
    private val getCategoryEntriesUseCase: GetCategoryEntriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(getInitialState())
    val state: StateFlow<CompendiumCategoryEntriesState> = _state

    fun interact(action: CompendiumCategoryEntriesAction) {
        when (action) {
            CompendiumCategoryEntriesAction.LoadData -> fetchEntries()
        }
    }

    private fun getInitialState() = CompendiumCategoryEntriesState(
        categoryName = categoryName,
        bannerUrl = BANNER_URL,
        entries = Async.Loading
    )

    private fun fetchEntries() = viewModelScope.launch {
        setLoadingEntries()
        getCategoryEntriesUseCase(categoryName)
            .onSuccess { setLoadedEntries(it) }
            .onFailure { setErrorState(it) }
    }

    private fun setErrorState(error: Throwable) {
        _state.update { it.copy(entries = Async.Error(error.message)) }
    }

    private fun setLoadedEntries(entries: List<Entry>) {
        _state.update { it.copy(entries = Async.Success(entries.toEntryModel())) }
    }

    private fun List<Entry>.toEntryModel() = map {
        EntryModel(
            name = it.name,
            image = it.image,
            locations = if (it.locations.isEmpty()) "" else it.locations.reduce { acc, location ->
                if (acc.isBlank()) location else "$acc, $location"
            }
        )
    }

    private fun setLoadingEntries() {
        _state.update { it.copy(entries = Async.Loading) }
    }
}
