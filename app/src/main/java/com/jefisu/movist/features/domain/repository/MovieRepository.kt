package com.jefisu.movist.features.domain.repository

import com.jefisu.movist.features.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(): Flow<List<Movie>>

    suspend fun getMovieById(id: Int): Movie

    suspend fun insertMovie(Movie: Movie)

    suspend fun deleteMovie(movie: Movie)
}