package com.jefisu.movist.features.data.repository

import com.jefisu.movist.features.data.database.MovieDao
import com.jefisu.movist.features.data.model.Movie
import com.jefisu.movist.features.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val movieDao: MovieDao
) : MovieRepository {

    override fun getMovies(): Flow<List<Movie>> {
        return movieDao.getMovies()
    }

    override suspend fun getMovieById(id: Int): Movie {
        return movieDao.getMovieById(id)
    }

    override suspend fun insertMovie(Movie: Movie) {
        movieDao.insertMovie(Movie)
    }

    override suspend fun deleteMovie(movie: Movie) {
       movieDao.deleteMovie(movie)
    }
}