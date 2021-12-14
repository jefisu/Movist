package com.jefisu.movist.features.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jefisu.movist.features.domain.model.Movie

@Entity(tableName = "movie")
data class MovieDto(
    val title: String = "",
    val description: String = "",
    val watched:  Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
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

class InvalidMovieException(message: String) : Exception(message)