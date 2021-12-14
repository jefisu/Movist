package com.jefisu.movist.features.presentation.home

import com.jefisu.movist.features.domain.model.Movie

sealed class HomeEvent {
    data class DeleteMovie(val movie: Movie) : HomeEvent()
    object RestoreMovie : HomeEvent()
}
