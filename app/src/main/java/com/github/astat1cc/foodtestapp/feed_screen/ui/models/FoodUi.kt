package com.github.astat1cc.foodtestapp.feed_screen.ui.models

import com.github.astat1cc.foodtestapp.feed_screen.domain.models.FoodDomain

data class FoodUi(
    val id: String,
    val name: String,
    val image: String
) {

    companion object {

        fun fromDomain(food: FoodDomain) = with(food) {
            FoodUi(id, name, image)
        }
    }
}