package com.jefisu.movist.features.data.database

import androidx.room.*
import com.jefisu.movist.features.data.model.MovieDto
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getMovies() : Flow<List<MovieDto>>

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovieById(id: Int) : MovieDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieDto: MovieDto)

    @Delete
    suspend fun deleteMovie(movie: MovieDto)
}