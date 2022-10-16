package com.github.astat1cc.foodtestapp

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.astat1cc.foodtestapp.feed_screen.ui.FeedScreen
import com.github.astat1cc.foodtestapp.navigation.Screen
import com.github.astat1cc.foodtestapp.ui.theme.BottomNavBackgroundGray
import com.github.astat1cc.foodtestapp.ui.theme.BottomNavItemGray
import com.github.astat1cc.foodtestapp.ui.theme.Pink

@Composable
fun ApplicationScreen() {

    val navController = rememberNavController()
    val screens = listOf(
        Screen.Feed,
        Screen.Profile,
        Screen.Cart
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = BottomNavBackgroundGray) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                screens.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.iconId),
                                contentDescription = null,
//                                tint = BottomNavItemGray
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(screen.resourceId),
//                                color = BottomNavItemGray
                            )
                        },
                        selectedContentColor = Pink,
                        unselectedContentColor = BottomNavItemGray,
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            if (screen !is Screen.Feed) return@BottomNavigationItem
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = Screen.Feed.route) {
            composable(Screen.Feed.route) { FeedScreen(navController) }
        }
    }
}