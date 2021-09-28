package com.jefisu.movist.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    val title: String = "",
    val description: String = "",
    val watched:  Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)

class InvalidMovieException(message: String) : Exception(message)
