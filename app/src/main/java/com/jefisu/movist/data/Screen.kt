package com.jefisu.movist.data

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash")
    object HomeScreen : Screen("Home")
    object RegisterScreen : Screen("Register")
}