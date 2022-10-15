package com.github.astat1cc.foodtestapp.feed_screen.domain

import com.github.astat1cc.foodtestapp.feed_screen.domain.models.FoodCategory
import com.github.astat1cc.foodtestapp.feed_screen.domain.models.FoodDomain

interface FeedRepository {

    suspend fun fetchFood(category: String): List<FoodDomain>
}