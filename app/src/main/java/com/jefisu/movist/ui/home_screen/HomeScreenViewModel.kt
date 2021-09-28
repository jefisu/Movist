package com.jefisu.movist.ui.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jefisu.movist.data.dto.Movie
import com.jefisu.movist.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
   repository: MovieRepository
) : ViewModel() {

    private val _movies = repository.getMovies().asLiveData()
    val movies: LiveData<List<Movie>> = _movies
}