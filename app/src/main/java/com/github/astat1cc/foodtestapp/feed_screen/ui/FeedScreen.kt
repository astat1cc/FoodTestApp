package com.github.astat1cc.foodtestapp.feed_screen.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.github.astat1cc.foodtestapp.R
import com.github.astat1cc.foodtestapp.core.UiState
import com.github.astat1cc.foodtestapp.feed_screen.ui.viewmodel.FeedViewModel
import com.github.astat1cc.foodtestapp.feed_screen.ui.views.AddressAndQrCodeRow
import com.github.astat1cc.foodtestapp.feed_screen.ui.views.Banner
import com.github.astat1cc.foodtestapp.feed_screen.ui.views.Category
import com.github.astat1cc.foodtestapp.feed_screen.ui.views.FoodView
import com.github.astat1cc.foodtestapp.ui.theme.Shapes
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import me.onebone.toolbar.*
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun FeedScreen(
    navController: NavController,
    viewModel: FeedViewModel = getViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val chosenCategory = viewModel.chosenCategory.collectAsState()
    val collapsingToolbarState = rememberCollapsingToolbarScaffoldState()
    val lazyListState = rememberLazyListState()

    Column {
        AddressAndQrCodeRow()

        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            state = collapsingToolbarState,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbarModifier = if (uiState.value is UiState.Success) {
                Modifier.verticalScroll(rememberScrollState())
            } else {
                Modifier
            },
            toolbar = {
                LazyRow(
                    modifier = Modifier
                        .padding(top = 36.dp)
                        .fillMaxWidth()
                        .height(150.dp)
                        .parallax(1f),
                    flingBehavior = rememberSnapperFlingBehavior(lazyListState),
                    state = lazyListState,
                    contentPadding = PaddingValues(end = 16.dp)
                ) {
                    item { Banner(imageResourceId = R.drawable.banner1) }
                    item { Banner(imageResourceId = R.drawable.banner2) }
                    item { Banner(imageResourceId = R.drawable.banner1) }
                }
                Box(modifier = Modifier.size(0.dp))
            }
        ) {
            when (val localUiState = uiState.value) {
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 40.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is UiState.Success -> {
                    Column {
                        Box {
                            LazyRow(
                                modifier = Modifier
                                    .padding(top = 8.dp)
                                    .shadow(
                                        elevation = if (collapsingToolbarState.toolbarState.height == 0) {
                                            16.dp
                                        } else {
                                            0.dp
                                        }
                                    )
                                    .background(Color.White)
                                    .padding(vertical = 16.dp)
                            ) {
                                items(viewModel.categoryList) { category ->
                                    Category(
                                        isChosen = category == chosenCategory.value,
                                        category = category,
                                        onClick = { clickedCategory ->
                                            viewModel.choseCategory(clickedCategory)
                                        })
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .background(MaterialTheme.colors.background)
                            )
                        }
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            contentPadding = PaddingValues(bottom = 32.dp)
                        ) {
                            items(localUiState.data) { food ->
                                FoodView(food) {

                                }
                            }
                        }
                    }
                }
                is UiState.Fail -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 40.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Text(text = localUiState.message)
                    }
                }
            }
        }
    }
}