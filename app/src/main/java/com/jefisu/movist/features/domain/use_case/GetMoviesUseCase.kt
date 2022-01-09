package com.jefisu.movist.features.domain.use_case

import com.jefisu.movist.features.domain.model.Movie
import com.jefisu.movist.features.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<List<Movie>> {
        return repository.getMovies()
    }
}