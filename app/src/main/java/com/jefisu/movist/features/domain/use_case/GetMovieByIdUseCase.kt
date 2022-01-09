package com.jefisu.movist.features.domain.use_case

import com.jefisu.movist.features.domain.repository.MovieRepository
import com.jefisu.movist.features.domain.model.Movie

class GetMovieByIdUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(id: Int): Movie {
        return repository.getMovieById(id).toMovie()
    }
}