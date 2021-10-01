package com.jefisu.movist.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jefisu.movist.data.ScreenRoute
import com.jefisu.movist.ui.home.HomeScreen
import com.jefisu.movist.ui.register.RegisterScreen
import com.jefisu.movist.ui.theme.MovistTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovistTheme {
                val navController = rememberNavController()
                NavigationMethod(navController = navController)
            }
        }
    }
}

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