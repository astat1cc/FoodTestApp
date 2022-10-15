package com.github.astat1cc.foodtestapp.feed_screen.domain

import com.github.astat1cc.foodtestapp.feed_screen.domain.models.FetchResult
import com.github.astat1cc.foodtestapp.feed_screen.domain.models.FoodDomain

interface FeedInteractor {

    suspend fun fetchFeed(category: String): FetchResult<List<FoodDomain>>

    class Impl(
        private val repository: FeedRepository
    ) : FeedInteractor {

        override suspend fun fetchFeed(category: String): FetchResult<List<FoodDomain>> {
            try {
                val foodList = repository.fetchFood(category)
                FetchResult.Success(foodList)
            } catch (e: Exception) {

            }
        }
    }
}