package com.jefisu.movist.features.presentation.add_edit

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefisu.movist.features.presentation.add_edit.components.TransparentHintTextField
import com.jefisu.movist.ui.theme.spaces
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterialApi
@Composable
fun AddEditScreen(
    navController: NavController,
    viewModel: AddEditViewModel = hiltViewModel()
) {
    val titleState = viewModel.movieTitle.value
    val descriptionState = viewModel.movieDescription.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditViewModel.UiEvent.SaveMovie -> {
                    navController.navigateUp()
                }
            }
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditEvent.SaveMovie)
                }
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spaces.medium)
        ) {
            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = TextStyle(
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    letterSpacing = MaterialTheme.typography.h5.letterSpacing
                )
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spaces.small))
            TransparentHintTextField(
                text = descriptionState.text,
                hint = descriptionState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditEvent.EnteredDescription(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditEvent.ChangeDescriptionFocus(it))
                },
                isHintVisible = descriptionState.isHintVisible,
                textStyle = TextStyle(
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    letterSpacing = MaterialTheme.typography.body1.letterSpacing
                ),
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}