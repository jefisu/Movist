package com.jefisu.movist.features.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.movist.features.data.model.InvalidMovieException
import com.jefisu.movist.features.data.model.MovieDto
import com.jefisu.movist.features.domain.use_case.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private var recentlyDeletedMovieDto: MovieDto? = null

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var getMovieJob: Job? = null

    init {
        getMovies()
    }

    private fun getMovies() {
        getMovieJob?.cancel()
        getMovieJob = movieUseCase.getMovies()
            .onEach { movies ->
                _state.value = state.value.copy(
                    movies = movies.map { it.toMovie() }
                )
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.RestoreMovie -> {
                viewModelScope.launch {
                    try {
                        movieUseCase.insert(recentlyDeletedMovieDto ?: return@launch)
                        recentlyDeletedMovieDto = null
                    } catch (e: InvalidMovieException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't restore movie"
                            )
                        )
                    }
                }
            }
            is HomeEvent.DeleteMovie -> {
                viewModelScope.launch {
                    try {
                        movieUseCase.delete(event.movie.toMovieDto())
                    } catch (e: InvalidMovieException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't delete movie"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
    }
}