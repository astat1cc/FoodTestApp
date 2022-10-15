package com.github.astat1cc.foodtestapp.feed_screen.domain.models

sealed class FetchResult<T> {

    class Success<T>(val data: T) : FetchResult<T>()
    class Fail<T>(error: ErrorType) : FetchResult<T>()
}