package com.jefisu.movist.features.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jefisu.movist.features.data.model.MovieDto

@Database(entities = [MovieDto::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao

    companion object {
        const val DATABASE_NAME = "movie_db"
    }
}