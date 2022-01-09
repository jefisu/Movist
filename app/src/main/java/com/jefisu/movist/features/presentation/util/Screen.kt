package com.jefisu.movist.features.presentation.util

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Add : Screen("add")
    object Edit : Screen("edit")
}