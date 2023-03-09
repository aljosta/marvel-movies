package com.example.marvelmovies.comics.di

import android.content.Context
import androidx.room.Room
import com.example.marvelmovies.data.database.AppDatabase
import com.example.marvelmovies.data.network.ApiServiceProvider
import com.example.marvelmovies.comics.data.datasources.ComicLocalDataSource
import com.example.marvelmovies.comics.data.datasources.ComicRemoteDataSource
import com.example.marvelmovies.comics.data.repositories.ComicDataRepository
import com.example.marvelmovies.comics.domain.ComicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ComicsModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(): ComicRemoteDataSource =
        ApiServiceProvider().create(
            ComicRemoteDataSource::class.java,
            "https://gateway.marvel.com/",
        )

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "comic-database",
        ).build()
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(database: AppDatabase): ComicLocalDataSource =
        database.comicDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: ComicRemoteDataSource,
        localDataSource: ComicLocalDataSource,
    ): ComicRepository = ComicDataRepository(remoteDataSource, localDataSource)
}
