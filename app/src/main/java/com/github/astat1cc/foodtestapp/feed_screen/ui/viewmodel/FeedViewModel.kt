package com.github.astat1cc.foodtestapp.feed_screen.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.astat1cc.foodtestapp.core.AppErrorHandler
import com.github.astat1cc.foodtestapp.core.FetchResult
import com.github.astat1cc.foodtestapp.core.UiState
import com.github.astat1cc.foodtestapp.feed_screen.domain.FeedInteractor
import com.github.astat1cc.foodtestapp.feed_screen.domain.models.FoodCategory
import com.github.astat1cc.foodtestapp.feed_screen.domain.models.FoodDomain
import com.github.astat1cc.foodtestapp.feed_screen.ui.models.FoodUi
import kotlinx.coroutines.flow.*

class FeedViewModel(
    private val interactor: FeedInteractor,
    private val errorHandler: AppErrorHandler
) : ViewModel() {

    private val _chosenCategory = MutableStateFlow<FoodCategory>(FoodCategory.Beef)
    val chosenCategory = _chosenCategory.asStateFlow()

    val uiState: StateFlow<UiState<List<FoodUi>>> = chosenCategory.map { category ->
        val fetchResult = interactor.fetchFeed(category)
        fetchResult.toUiState()
    }.stateIn(viewModelScope, SharingStarted.Lazily, UiState.Loading())

    val categoryList = FoodCategory.values()

    fun choseCategory(newCategory: FoodCategory) {
        _chosenCategory.value = newCategory
    }

    private fun FetchResult<List<FoodDomain>>.toUiState(): UiState<List<FoodUi>> =
        when (this) {
            is FetchResult.Success -> {
                UiState.Success(
                    data.map { foodDomain ->
                        FoodUi.fromDomain(foodDomain)
                    }
                )
            }
            is FetchResult.Fail -> {
                UiState.Fail(
                    message = errorHandler.getErrorMessageOf(error)
                )
            }
        }
}