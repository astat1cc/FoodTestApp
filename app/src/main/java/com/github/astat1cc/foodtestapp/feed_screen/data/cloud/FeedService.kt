package com.github.astat1cc.foodtestapp.feed_screen.data.cloud

import com.github.astat1cc.foodtestapp.feed_screen.data.cloud.models.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedService {

    @GET("")
    suspend fun fetchFood(@Query("c") category: String): NetworkResponse
}