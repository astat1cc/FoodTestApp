package com.github.astat1cc.foodtestapp.feed_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.astat1cc.foodtestapp.R
import com.github.astat1cc.foodtestapp.core.UiState
import com.github.astat1cc.foodtestapp.feed_screen.ui.viewmodel.FeedViewModel
import com.github.astat1cc.foodtestapp.feed_screen.ui.views.*
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import me.onebone.toolbar.*
import org.koin.androidx.compose.getViewModel

@Composable
fun FeedScreen(
    navController: NavController,
    viewModel: FeedViewModel = getViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val chosenCategory = viewModel.chosenCategory.collectAsState()
    val collapsingToolbarState = rememberCollapsingToolbarScaffoldState()

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
                BannerRow(modifier = Modifier.parallax(1f))
                Box(modifier = Modifier.size(0.dp))
            }
        ) {
            Column {
                CategoryRow(
                    collapsingToolbarState = collapsingToolbarState,
                    categoryList = viewModel.categoryList,
                    chosenCategory = chosenCategory.value,
                    onClick = { clickedCategory ->
                        viewModel.choseCategory(clickedCategory)
                    }
                )

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
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            contentPadding = PaddingValues(bottom = 80.dp)
                        ) {
                            items(localUiState.data) { food ->
                                FoodView(food) {

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
                            Column {
                                Text(text = localUiState.message)
                                Button(
                                    modifier = Modifier.padding(top = 8.dp),
                                    onClick = { viewModel.tryAgain() }) {
                                    Text(text = stringResource(id = R.string.try_again))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}