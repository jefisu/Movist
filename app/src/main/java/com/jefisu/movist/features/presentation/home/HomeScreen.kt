package com.jefisu.movist.features.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.movist.features.presentation.home.components.MoveItem
import com.jefisu.movist.features.presentation.util.Screen
import com.jefisu.movist.ui.theme.spaces
import kotlinx.coroutines.flow.collect

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is HomeViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spaces.medium)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your list",
                    style = MaterialTheme.typography.h4,
                )
                IconButton(
                    onClick = { navController.navigate(Screen.Add.route) },
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (isSystemInDarkTheme()) MaterialTheme.colors.onSurface.copy(0.1f)
                            else Color.DarkGray.copy(0.2f)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add movie",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spaces.medium))
            LazyColumn {
                items(viewModel.state.value.movies) { movie ->
                    MoveItem(
                        movie = movie,
                        onDeleteClick = viewModel::deleteMovie,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.Edit.route + "?id=${movie.id}"
                                )
                            }
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spaces.medium))
                }
            }
        }
    }
}