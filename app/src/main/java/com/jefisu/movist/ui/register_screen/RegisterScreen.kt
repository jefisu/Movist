package com.jefisu.movist.ui.register_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.movist.data.ScreenRoute
import com.jefisu.movist.ui.register_screen.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterialApi
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterScreenViewModel = hiltViewModel()
) {

    val titleState = viewModel.movieTitle.value
    val descriptionState = viewModel.movieDescription.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is RegisterScreenViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(RegisterEvent.SaveMovie)
                    navController.navigate(ScreenRoute.HomeScreen.route)
                }
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(RegisterEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5,
            )
            Spacer(modifier = Modifier.height(10.dp))
            TransparentHintTextField(
                text = descriptionState.text,
                hint = descriptionState.hint,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredDescription(it))
                },
                onFocusChange = {
                    viewModel.onEvent(RegisterEvent.ChangeDescriptionFocus(it))
                },
                isHintVisible = descriptionState.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}
