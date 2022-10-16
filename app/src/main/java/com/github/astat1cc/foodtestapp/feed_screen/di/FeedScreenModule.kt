package com.github.astat1cc.foodtestapp.feed_screen.di

import com.github.astat1cc.foodtestapp.core.database.AppDatabase
import com.github.astat1cc.foodtestapp.feed_screen.data.FeedRepositoryImpl
import com.github.astat1cc.foodtestapp.feed_screen.data.cloud.FeedService
import com.github.astat1cc.foodtestapp.feed_screen.domain.FeedInteractor
import com.github.astat1cc.foodtestapp.feed_screen.domain.FeedRepository
import com.github.astat1cc.foodtestapp.feed_screen.ui.viewmodel.FeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val feedScreenModule = module {
    single {
        provideFeedService(retrofit = get())
    }
    single {
        provideFeedDao(database = get())
    }
    single<FeedRepository> {
        FeedRepositoryImpl(service = get(), dao = get())
    }
    single<FeedInteractor> {
        FeedInteractor.Impl(repository = get(), errorHandler = get(), dispatchers = get())
    }
    viewModel {
        FeedViewModel(interactor = get(), errorHandler = get(), dispatchers = get())
    }
}

fun provideFeedDao(database: AppDatabase) = database.feedDao()

fun provideFeedService(retrofit: Retrofit): FeedService = retrofit.create(FeedService::class.java)
