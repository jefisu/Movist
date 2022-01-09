package com.jefisu.movist.features.data.database

import androidx.room.*
import com.jefisu.movist.features.domain.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovieById(id: Int): Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(Movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)
}