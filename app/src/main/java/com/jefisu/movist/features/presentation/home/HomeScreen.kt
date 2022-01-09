package com.jefisu.movist.features.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.movist.features.presentation.home.components.NoteItem
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
        modifier = Modifier.fillMaxWidth(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.Add.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spaces.medium)
        ) {
            Text(
                text = "Your list",
                style = MaterialTheme.typography.h4,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spaces.small))
            LazyColumn {
                items(viewModel.state.value.movies) { movie ->
                    NoteItem(
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