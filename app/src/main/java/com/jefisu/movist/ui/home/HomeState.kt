package com.jefisu.movist.ui.home

import com.jefisu.movist.data.dto.Movie

data class HomeState(
    val movies: List<Movie> = emptyList()
)
