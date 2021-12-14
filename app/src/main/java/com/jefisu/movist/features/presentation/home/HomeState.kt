package com.jefisu.movist.features.presentation.home

import com.jefisu.movist.features.domain.model.Movie

data class HomeState(
    val movies: List<Movie> = emptyList()
)
