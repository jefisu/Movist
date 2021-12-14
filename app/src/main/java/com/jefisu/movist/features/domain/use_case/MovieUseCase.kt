package com.jefisu.movist.features.domain.use_case

data class MovieUseCase(
    val insert: InsertMovieUseCase,
    val getMovies: GetMoviesUseCase,
    val getMovieById: GetMovieByIdUseCase,
    val delete: DeleteMovieUseCase
)
