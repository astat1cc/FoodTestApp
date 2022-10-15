package com.github.astat1cc.foodtestapp.feed_screen.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.github.astat1cc.foodtestapp.feed_screen.domain.FeedInteractor

class FeedViewModel(
    private val interactor: FeedInteractor
) : ViewModel() {
}