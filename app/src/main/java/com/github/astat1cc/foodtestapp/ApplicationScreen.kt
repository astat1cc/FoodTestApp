package com.github.astat1cc.foodtestapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.astat1cc.foodtestapp.feed_screen.ui.FeedScreen
import com.github.astat1cc.foodtestapp.navigation.NavigationTree

@Composable
fun ApplicationScreen() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationTree.FEED.name) {
        composable(NavigationTree.FEED.name) { FeedScreen(navController) }
    }
}