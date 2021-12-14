package com.jefisu.movist.features.domain.use_case

import com.jefisu.movist.features.data.repository.MovieRepository
import com.jefisu.movist.features.data.model.MovieDto

class InsertMovieUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieDto: MovieDto) {
        repository.insertMovie(movieDto)
    }
}