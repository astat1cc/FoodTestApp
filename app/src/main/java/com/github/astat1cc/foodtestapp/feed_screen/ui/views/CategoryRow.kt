package com.github.astat1cc.foodtestapp.feed_screen.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.astat1cc.foodtestapp.feed_screen.domain.models.FoodCategory
import me.onebone.toolbar.CollapsingToolbarScaffoldState

@Composable
fun CategoryRow(
    collapsingToolbarState: CollapsingToolbarScaffoldState,
    categoryList: List<FoodCategory>,
    chosenCategory: FoodCategory,
    onClick: (FoodCategory) -> Unit
) {
    val categoryLazyListState = rememberLazyListState()

    Box {
        LazyRow(
            modifier = Modifier
                .padding(top = 8.dp)
                .shadow(
                    elevation = if (collapsingToolbarState.toolbarState.height == 0) {
                        8.dp
                    } else {
                        0.dp
                    }
                )
                .background(Color.White)
                .padding(vertical = 16.dp),
            state = categoryLazyListState
        ) {
            items(categoryList) { category ->
                Category(
                    isChosen = category == chosenCategory,
                    category = category,
                    onClick = { onClick(category) })
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .background(MaterialTheme.colors.background)
        )
    }
}