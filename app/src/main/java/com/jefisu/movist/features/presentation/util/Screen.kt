package com.jefisu.movist.features.presentation.util

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash")
    object HomeScreen : Screen("Home")
    object RegisterScreen : Screen("Register")
}