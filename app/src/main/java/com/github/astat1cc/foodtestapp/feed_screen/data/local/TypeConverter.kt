package com.github.astat1cc.foodtestapp.feed_screen.data.local

import androidx.room.TypeConverter
import com.github.astat1cc.foodtestapp.feed_screen.data.models.FoodData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TypeConverter {

    @TypeConverter
    fun fromJson(foodListJson: String): List<FoodData> {
        val type = object : TypeToken<List<FoodData>>() {}.type
        return Gson().fromJson(foodListJson, type)
    }

    @TypeConverter
    fun toJson(foodList: List<FoodData>): String {
        val type = object : TypeToken<List<FoodData>>() {}.type
        return Gson().toJson(foodList, type)
    }
}