package com.jefisu.movist.features.data.repository

import com.jefisu.movist.features.data.database.MovieDao
import com.jefisu.movist.features.data.model.MovieDto
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val movieDao: MovieDao
) : MovieRepository {

    override fun getMovies(): Flow<List<MovieDto>> {
        return movieDao.getMovies()
    }

    override suspend fun getMovieById(id: Int): MovieDto {
        return movieDao.getMovieById(id)
    }

    override suspend fun insertMovie(movieDto: MovieDto) {
        movieDao.insertMovie(movieDto)
    }

    override suspend fun deleteMovie(movie: MovieDto) {
       movieDao.deleteMovie(movie)
    }
}