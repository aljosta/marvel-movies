package com.example.marvelmovies.data.database

import androidx.room.*
import com.example.marvelmovies.comics.data.datasources.ComicLocalDataSource
import com.example.marvelmovies.comics.data.models.ComicEntity

@Database(entities = [ComicEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun comicDao(): ComicLocalDataSource
}
