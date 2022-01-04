package com.jefisu.movist.features.domain.use_case

import com.jefisu.movist.features.data.model.MovieDto
import com.jefisu.movist.features.data.repository.MovieRepository

class InsertMovieUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(title: String, description: String, id: Int? = null) {
        if (id != null) {
            val movie = MovieDto(title, description, id = id)
            repository.insertMovie(movie)
        } else {
            val movie = MovieDto(title, description)
            repository.insertMovie(movie)
        }
    }
}