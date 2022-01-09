package com.jefisu.movist.features.domain.model

import com.jefisu.movist.features.data.model.Movie

data class Movie(
    val title: String,
    val description: String,
    val watched: Boolean,
    val id: Int?
) {
    fun toMovie(): Movie {
        return Movie(
            title = title,
            description = description,
            watched = watched,
            id = id
        )
    }
}
