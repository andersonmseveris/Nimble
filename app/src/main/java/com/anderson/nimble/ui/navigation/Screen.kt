package com.anderson.nimble.ui.navigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object LoadScreen : Screen("load_screen")
}