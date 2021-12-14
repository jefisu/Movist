package com.jefisu.movist.features.presentation.util

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jefisu.movist.features.presentation.home.HomeScreen
import com.jefisu.movist.features.presentation.register.RegisterScreen
import com.jefisu.movist.features.presentation.splash.SplashScreen

@ExperimentalMaterialApi
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.RegisterScreen.route
        ) {
            RegisterScreen(navController = navController)
        }
    }
}