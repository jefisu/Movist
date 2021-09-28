package com.jefisu.movist.data

sealed class ScreenRoute(val route: String) {
    object HomeScreen : ScreenRoute("Home")
    object RegisterScreen : ScreenRoute("Register")
}