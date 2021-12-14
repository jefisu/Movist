package com.jefisu.movist.features.domain.use_case

import com.jefisu.movist.features.data.model.MovieDto
import com.jefisu.movist.features.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<List<MovieDto>> {
        return repository.getMovies()
    }
}