package com.github.astat1cc.foodtestapp.core.di

import androidx.room.Room
import com.github.astat1cc.foodtestapp.core.AppDispatchersProvider
import com.github.astat1cc.foodtestapp.core.AppErrorHandler
import com.github.astat1cc.foodtestapp.core.database.AppDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

val coreModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single {
        OkHttpClient.Builder().addInterceptor(get() as HttpLoggingInterceptor).build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<AppDispatchersProvider> {
        AppDispatchersProvider.Impl()
    }
    single<AppErrorHandler> {
        AppErrorHandler.Impl(androidContext())
    }
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, AppDatabase.NAME).build()
    }
}