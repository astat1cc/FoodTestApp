package com.github.astat1cc.foodtestapp.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface AppDispatchersProvider {

    fun io(): CoroutineDispatcher

    fun default(): CoroutineDispatcher

    class Impl : AppDispatchersProvider {

        override fun io(): CoroutineDispatcher = Dispatchers.IO

        override fun default(): CoroutineDispatcher = Dispatchers.Default
    }
}