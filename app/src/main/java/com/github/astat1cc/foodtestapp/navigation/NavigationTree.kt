package com.github.astat1cc.foodtestapp.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.astat1cc.foodtestapp.R

enum class NavigationTree {
    PROFILE, FEED, CART
}

sealed class Screen(val route: String, @StringRes val resourceId: Int, @DrawableRes val iconId: Int) {
    object Profile : Screen("profile", R.string.profile, R.drawable.ic_profile)
    object Feed : Screen("feed", R.string.menu, R.drawable.ic_menu)
    object Cart : Screen("cart", R.string.cart, R.drawable.ic_cart)
}