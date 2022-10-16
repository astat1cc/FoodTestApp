package com.github.astat1cc.foodtestapp.feed_screen.data

import com.github.astat1cc.foodtestapp.feed_screen.data.cloud.FeedService
import com.github.astat1cc.foodtestapp.feed_screen.data.local.CategoryDb
import com.github.astat1cc.foodtestapp.feed_screen.data.local.FeedDao
import com.github.astat1cc.foodtestapp.feed_screen.domain.FeedRepository
import java.net.UnknownHostException

class FeedRepositoryImpl(
    private val service: FeedService,
    private val dao: FeedDao
) : FeedRepository {

    override suspend fun fetchFood(category: String) =
        try {
            val networkData = service.fetchFood(category).meals
            dao.saveFood(
                CategoryDb(category, networkData)
            )
            networkData.map { foodData ->
                foodData.toDomain()
            }
        } catch (e: UnknownHostException) { // if no network connection
            val localCategory = dao.fetchFood(category) ?: throw e
            localCategory.meals.map { foodData -> foodData.toDomain() }
        }
}