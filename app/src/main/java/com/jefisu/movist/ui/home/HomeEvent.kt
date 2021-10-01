package com.jefisu.movist.ui.home

import com.jefisu.movist.data.dto.Movie

sealed class HomeEvent {
    data class DeleteMovie(val movie: Movie) : HomeEvent()
    data class ChangeStatusColor(val status: Boolean) : HomeEvent()
    object RestoreMovie : HomeEvent()
}
