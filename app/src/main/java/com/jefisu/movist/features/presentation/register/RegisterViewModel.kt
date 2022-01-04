package com.jefisu.movist.features.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.movist.features.data.model.InvalidMovieException
import com.jefisu.movist.features.domain.model.Movie
import com.jefisu.movist.features.domain.use_case.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _movieTitle = mutableStateOf(MovieTextFieldState(hint = "Enter title"))
    val movieTitle: State<MovieTextFieldState> = _movieTitle

    private val _movieDescription = mutableStateOf(MovieTextFieldState(hint = "Enter description"))
    val movieDescription: State<MovieTextFieldState> = _movieDescription

    private var _currentMovie: Movie? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Int>("id")?.let { movieId ->
            if (movieId != -1) {
                viewModelScope.launch {
                    _currentMovie = movieUseCase.getMovieById(movieId)
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

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EnteredTitle -> {
                _movieTitle.value = movieTitle.value.copy(text = event.value)
            }
            is RegisterEvent.ChangeTitleFocus -> {
                _movieTitle.value = movieTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && _movieTitle.value.text.isBlank()
                )
            }
            is RegisterEvent.EnteredDescription -> {
                _movieDescription.value = movieDescription.value.copy(text = event.value)
            }
            is RegisterEvent.ChangeDescriptionFocus -> {
                _movieDescription.value = movieDescription.value.copy(
                    isHintVisible = !event.focusState.isFocused && _movieDescription.value.text.isBlank()
                )
            }
            is RegisterEvent.SaveMovie -> {
                viewModelScope.launch {
                    try {
                        if (event.id != null) {
                            movieUseCase.insert(
                                title = _movieTitle.value.text,
                                description = _movieDescription.value.text,
                                id = event.id
                            )
                        } else {
                            movieUseCase.insert(
                                title = _movieTitle.value.text,
                                description = _movieDescription.value.text
                            )
                        }
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