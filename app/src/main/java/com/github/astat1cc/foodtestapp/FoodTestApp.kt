package com.github.astat1cc.foodtestapp

import android.app.Application
import com.github.astat1cc.foodtestapp.core.di.coreModule
import com.github.astat1cc.foodtestapp.feed_screen.di.feedScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FoodTestApp:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FoodTestApp)
            modules(coreModule, feedScreenModule)
        }
    }
}