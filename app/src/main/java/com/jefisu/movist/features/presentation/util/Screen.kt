package com.jefisu.movist.features.presentation.util

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Register : Screen("register")
    object Update : Screen("update")
}