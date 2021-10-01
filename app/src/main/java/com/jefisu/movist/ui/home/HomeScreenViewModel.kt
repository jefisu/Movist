package com.jefisu.movist.ui.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefisu.movist.data.dto.Movie
import com.jefisu.movist.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private var recentlyDeletedMovie: Movie? = null

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    private var getMovieJob: Job? = null

    init {
        getMovies()
    }

    private fun getMovies() {
        getMovieJob?.cancel()
        getMovieJob = repository.getMovies()
            .onEach { movies ->
                _state.value = state.value.copy(
                    movies = movies
                )
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.RestoreMovie -> {
                viewModelScope.launch {
                    try {
                        repository.insertMovie(recentlyDeletedMovie ?: return@launch)
                        recentlyDeletedMovie = null
                    } catch (e: Exception) {
                        Log.d("TAG", e.message.toString())
                    }
                }
            }
            is HomeEvent.DeleteMovie -> {
                viewModelScope.launch {
                    try {
                        repository.deleteMovie(event.movie)
                    } catch (e: Exception) {
                        Log.d("TAG", e.message.toString())
                    }
                }
            }
        }
    }
}