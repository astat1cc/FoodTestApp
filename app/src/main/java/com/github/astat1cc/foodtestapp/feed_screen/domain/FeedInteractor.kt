package com.github.astat1cc.foodtestapp.feed_screen.domain

import com.github.astat1cc.foodtestapp.core.AppDispatchersProvider
import com.github.astat1cc.foodtestapp.core.AppErrorHandler
import com.github.astat1cc.foodtestapp.core.FetchResult
import com.github.astat1cc.foodtestapp.feed_screen.domain.models.FoodCategory
import com.github.astat1cc.foodtestapp.feed_screen.domain.models.FoodDomain
import kotlinx.coroutines.withContext

interface FeedInteractor {

    suspend fun fetchFeed(category: FoodCategory): FetchResult<List<FoodDomain>>

    class Impl(
        private val repository: FeedRepository,
        private val errorHandler: AppErrorHandler,
        private val dispatchers: AppDispatchersProvider
    ) : FeedInteractor {

        override suspend fun fetchFeed(category: FoodCategory): FetchResult<List<FoodDomain>> =
            withContext(dispatchers.io()) {
                try {
                    val foodList = repository.fetchFood(category.name)
                    FetchResult.Success(foodList)
                } catch (e: Exception) {
                    FetchResult.Fail(errorHandler.getErrorTypeOf(exception = e))
                }
            }
    }
}