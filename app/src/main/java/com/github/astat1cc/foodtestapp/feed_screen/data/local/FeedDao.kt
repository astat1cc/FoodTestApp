package com.github.astat1cc.foodtestapp.feed_screen.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.astat1cc.foodtestapp.feed_screen.data.models.FoodData

@Dao
interface FeedDao {

    @Insert(entity = CategoryDb::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFood(category: CategoryDb)

    @Query("SELECT * FROM ${CategoryDb.TABLE_NAME} WHERE name = :category")
    suspend fun fetchFood(category: String): CategoryDb?
}