package com.jefisu.movist.features.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    val title: String,
    val description: String,
    val timestamp: Long = System.currentTimeMillis(),
    @PrimaryKey val id: Int? = null
)

class InvalidMovieException(message: String) : Exception(message)