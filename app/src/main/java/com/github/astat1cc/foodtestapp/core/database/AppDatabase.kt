package com.github.astat1cc.foodtestapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.astat1cc.foodtestapp.feed_screen.data.local.CategoryDb
import com.github.astat1cc.foodtestapp.feed_screen.data.local.FeedDao
import com.github.astat1cc.foodtestapp.feed_screen.data.local.TypeConverter
import com.github.astat1cc.foodtestapp.feed_screen.data.models.FoodData

@Database(
    entities = [CategoryDb::class],
    version = 1
)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun feedDao(): FeedDao

    companion object {

        const val NAME = "app_database"
    }
}