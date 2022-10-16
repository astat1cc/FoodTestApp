package com.github.astat1cc.foodtestapp.feed_screen.data.models

import com.github.astat1cc.foodtestapp.feed_screen.domain.models.FoodDomain

data class FoodData(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
) {

    fun toDomain() = FoodDomain(idMeal, strMeal, strMealThumb)
}