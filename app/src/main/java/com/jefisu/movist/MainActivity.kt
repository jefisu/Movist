package com.jefisu.movist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.rememberNavController
import com.jefisu.movist.ui.util.NavGraph
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
                NavGraph(navController = navController)
            }
        }
    }
}