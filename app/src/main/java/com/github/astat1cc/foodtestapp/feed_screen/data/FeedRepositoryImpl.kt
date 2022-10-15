package com.github.astat1cc.foodtestapp.feed_screen.data

import com.github.astat1cc.foodtestapp.feed_screen.data.cloud.FeedService
import com.github.astat1cc.foodtestapp.feed_screen.domain.FeedRepository
import com.github.astat1cc.foodtestapp.feed_screen.domain.models.FoodCategory

class FeedRepositoryImpl(
    private val service: FeedService,
//    private val dao: FeedDao todo implement cache
) : FeedRepository {

    override suspend fun fetchFood(category: String) =
        service.fetchFood(category).meals
            .map { foodData ->
                foodData.toDomain()
            }
}