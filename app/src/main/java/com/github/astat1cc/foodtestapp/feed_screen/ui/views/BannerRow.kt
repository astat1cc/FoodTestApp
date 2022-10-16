package com.github.astat1cc.foodtestapp.feed_screen.ui.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.astat1cc.foodtestapp.R
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun BannerRow(modifier: Modifier = Modifier) {
    val bannerLazyListState = rememberLazyListState()

    LazyRow(
        modifier = modifier
            .padding(top = 36.dp)
            .fillMaxWidth()
            .height(150.dp),
        flingBehavior = rememberSnapperFlingBehavior(bannerLazyListState),
        state = bannerLazyListState,
        contentPadding = PaddingValues(end = 16.dp)
    ) {
        item { Banner(imageResourceId = R.drawable.banner1) }
        item { Banner(imageResourceId = R.drawable.banner2) }
        item { Banner(imageResourceId = R.drawable.banner1) }
    }
}