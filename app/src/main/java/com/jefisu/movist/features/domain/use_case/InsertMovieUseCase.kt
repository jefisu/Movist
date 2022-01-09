package com.jefisu.movist.features.domain.use_case

import com.jefisu.movist.features.data.model.InvalidMovieException
import com.jefisu.movist.features.data.model.Movie
import com.jefisu.movist.features.domain.repository.MovieRepository

class InsertMovieUseCase(
    private val repository: MovieRepository
) {
    @Throws(InvalidMovieException::class)
    suspend operator fun invoke(movie: Movie) {
        if (movie.title.isBlank()) {
            throw InvalidMovieException("The title of the note can't be empty.")
        }
        if (movie.description.isBlank()) {
            throw InvalidMovieException("The content of the note can't be empty.")
        }
        repository.insertMovie(movie)
    }
}