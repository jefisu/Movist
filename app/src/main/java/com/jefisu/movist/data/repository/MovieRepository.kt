package com.jefisu.movist.data.repository

import com.jefisu.movist.data.database.MovieDao
import com.jefisu.movist.data.dto.Movie
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val movieDao: MovieDao
) {
    fun getMovies(): Flow<List<Movie>> {
        return movieDao.getMovies()
    }

    suspend fun getMovieById(id: Int): Movie {
        return movieDao.getMovieById(id)
    }

    suspend fun insertMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }

    suspend fun deleteMovie(movie: Movie){
        movieDao.deleteMovie(movie)
    }
}