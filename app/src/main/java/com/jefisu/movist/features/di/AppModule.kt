package com.jefisu.movist.features.di

import android.app.Application
import androidx.room.Room
import com.jefisu.movist.features.data.database.MovieDatabase
import com.jefisu.movist.features.domain.repository.MovieRepository
import com.jefisu.movist.features.data.repository.MovieRepositoryImpl
import com.jefisu.movist.features.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            MovieDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(db: MovieDatabase): MovieRepository {
        return MovieRepositoryImpl(db.movieDao)
    }

    @Provides
    @Singleton
    fun provideMovieUseCase(repository: MovieRepository): MovieUseCase {
        return MovieUseCase(
            insert = InsertMovieUseCase(repository),
            getMovies = GetMoviesUseCase(repository),
            getMovieById = GetMovieByIdUseCase(repository),
            delete = DeleteMovieUseCase(repository),
        )
    }
}