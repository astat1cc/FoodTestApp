package com.github.astat1cc.foodtestapp.feed_screen.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.astat1cc.foodtestapp.feed_screen.data.local.CategoryDb.Companion.TABLE_NAME
import com.github.astat1cc.foodtestapp.feed_screen.data.models.FoodData

@Entity(tableName = TABLE_NAME)
data class CategoryDb(
    @PrimaryKey val name: String,
    val meals: List<FoodData>
) {

    companion object {

        const val TABLE_NAME = "category"
    }
}