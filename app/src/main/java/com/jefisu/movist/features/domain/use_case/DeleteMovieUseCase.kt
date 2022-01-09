package com.jefisu.movist.features.domain.use_case

import com.jefisu.movist.features.domain.model.Movie
import com.jefisu.movist.features.domain.repository.MovieRepository

class DeleteMovieUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movie: Movie) {
        if (movie.id != 0) {
            repository.deleteMovie(movie)
        }
    }
}