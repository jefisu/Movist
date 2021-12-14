package com.jefisu.movist.features.domain.model

import com.jefisu.movist.features.data.model.MovieDto

data class Movie(
    val title: String,
    val description: String,
    val watched: Boolean,
    val id: Int?
) {
    fun toMovieDto(): MovieDto {
        return MovieDto(
            title = title,
            description = description,
            watched = watched,
            id = id
        )
    }
}
