package com.jefisu.movist.features.domain.use_case

import com.jefisu.movist.features.data.model.MovieDto
import com.jefisu.movist.features.data.repository.MovieRepository

class DeleteMovieUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movie: MovieDto) {
        if (movie.id != 0) {
            repository.deleteMovie(movie)
        }
    }
}