package com.jefisu.movist.features.data.repository

import com.jefisu.movist.features.data.model.MovieDto
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(): Flow<List<MovieDto>>

    suspend fun getMovieById(id: Int): MovieDto

    suspend fun insertMovie(movieDto: MovieDto)

    suspend fun deleteMovie(movie: MovieDto)
}