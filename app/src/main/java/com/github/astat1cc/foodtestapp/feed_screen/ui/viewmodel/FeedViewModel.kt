package com.github.astat1cc.foodtestapp.feed_screen.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.astat1cc.foodtestapp.core.AppDispatchersProvider
import com.github.astat1cc.foodtestapp.core.AppErrorHandler
import com.github.astat1cc.foodtestapp.core.FetchResult
import com.github.astat1cc.foodtestapp.core.UiState
import com.github.astat1cc.foodtestapp.feed_screen.domain.FeedInteractor
import com.github.astat1cc.foodtestapp.feed_screen.domain.models.FoodCategory
import com.github.astat1cc.foodtestapp.feed_screen.domain.models.FoodDomain
import com.github.astat1cc.foodtestapp.feed_screen.ui.models.FoodUi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FeedViewModel(
    private val interactor: FeedInteractor,
    private val errorHandler: AppErrorHandler,
    private val dispatchers: AppDispatchersProvider
) : ViewModel() {

    private val _chosenCategory = MutableStateFlow<FoodCategory>(FoodCategory.Beef)
    val chosenCategory = _chosenCategory.asStateFlow()

    private val _uiState = MutableStateFlow<UiState<List<FoodUi>>>(UiState.Loading())
    val uiState = _uiState.asStateFlow()
//    chosenCategory.map
//    { category ->
//        val fetchResult = interactor.fetchFeed(category)
//        fetchResult.toUiState()
//    }.stateIn(viewModelScope, SharingStarted.Lazily, UiState.Loading())

    val categoryList = FoodCategory.values().toList()

    init {
        viewModelScope.launch {
            chosenCategory.collect { category ->
                _uiState.value = UiState.Loading()
                withContext(dispatchers.io()) {
                    val fetchResult = interactor.fetchFeed(category)
                    _uiState.value = fetchResult.toUiState()
                }
            }
        }
    }

    fun choseCategory(newCategory: FoodCategory) {
        _chosenCategory.value = newCategory
    }

    fun tryAgain() {
        val currentCategory = chosenCategory.value
        _chosenCategory.value = categoryList.first { it != currentCategory }
        _chosenCategory.value = currentCategory
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