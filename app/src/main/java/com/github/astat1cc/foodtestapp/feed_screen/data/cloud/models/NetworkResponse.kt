package com.github.astat1cc.foodtestapp.feed_screen.data.cloud.models

import com.github.astat1cc.foodtestapp.feed_screen.data.models.FoodData

data class NetworkResponse(
    val meals: List<FoodData>
)