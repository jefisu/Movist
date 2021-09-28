package com.jefisu.movist.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jefisu.movist.data.ScreenRoute
import com.jefisu.movist.ui.home_screen.HomeScreen
import com.jefisu.movist.ui.register_screen.RegisterScreen

@ExperimentalMaterialApi
@Composable
fun NavigationMethod(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ScreenRoute.HomeScreen.route) {
        composable(route = ScreenRoute.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = ScreenRoute.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
    }
}
