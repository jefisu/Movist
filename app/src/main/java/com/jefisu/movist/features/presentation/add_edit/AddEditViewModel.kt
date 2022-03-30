package com.jefisu.movist.features.presentation.add_edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.movist.features.domain.model.InvalidMovieException
import com.jefisu.movist.features.domain.model.Movie
import com.jefisu.movist.features.domain.use_case.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _movieTitle = mutableStateOf(MovieTextFieldState(hint = "Title"))
    val movieTitle: State<MovieTextFieldState> = _movieTitle

    private val _movieDescription = mutableStateOf(MovieTextFieldState(hint = "Description"))
    val movieDescription: State<MovieTextFieldState> = _movieDescription

    private var _currentMovie: Movie? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        val movieId = savedStateHandle.get<Int>("id")
        movieId?.let {
            if (it != -1) {
                viewModelScope.launch {
                    _currentMovie = movieUseCase.getMovieById(it)
                    _currentMovie?.let { movie ->
                        _movieTitle.value = movieTitle.value.copy(
                            text = movie.title,
                            isHintVisible = false
                        )
                        _movieDescription.value = movieDescription.value.copy(
                            text = movie.description,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.EnteredTitle -> {
                _movieTitle.value = movieTitle.value.copy(text = event.value)
            }
            is AddEditEvent.ChangeTitleFocus -> {
                _movieTitle.value = movieTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && _movieTitle.value.text.isBlank()
                )
            }
            is AddEditEvent.EnteredDescription -> {
                _movieDescription.value = movieDescription.value.copy(text = event.value)
            }
            is AddEditEvent.ChangeDescriptionFocus -> {
                _movieDescription.value = movieDescription.value.copy(
                    isHintVisible = !event.focusState.isFocused && _movieDescription.value.text.isBlank()
                )
            }
            is AddEditEvent.SaveMovie -> {
                viewModelScope.launch {
                    try {
                        movieUseCase.insert(
                            Movie(
                                title = _movieTitle.value.text,
                                description = _movieDescription.value.text,
                                id = _currentMovie?.id
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveMovie)
                    } catch (e: InvalidMovieException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't save movie"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SaveMovie : UiEvent()
    }
}