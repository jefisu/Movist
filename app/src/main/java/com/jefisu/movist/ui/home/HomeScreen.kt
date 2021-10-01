package com.jefisu.movist.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.movist.data.ScreenRoute
import com.jefisu.movist.ui.home.components.PersonalizedCard

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val list = viewModel.state.value

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                modifier = Modifier.padding(
                    start = 15.dp,
                    top = 5.dp
                )
            ) {
                Text(text = "Your list", fontSize = 28.sp)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(ScreenRoute.RegisterScreen.route)
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
        LazyColumn {
            items(list.movies) {
                PersonalizedCard(movie = it)
            }
        }
    }
}