package com.jefisu.movist.features.presentation.util

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jefisu.movist.features.presentation.home.HomeScreen
import com.jefisu.movist.features.presentation.register.RegisterScreen
import com.jefisu.movist.features.presentation.splash.SplashScreen

@ExperimentalMaterialApi
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(navController = navController)
        }
        composable(
            route = Screen.Update.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val movieId = entry.arguments?.getInt("id")
            movieId?.let {
                RegisterScreen(id = it, navController = navController)
            }
        }
    }
}