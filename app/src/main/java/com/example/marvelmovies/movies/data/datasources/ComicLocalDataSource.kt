package com.example.marvelmovies.movies.data.datasources

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelmovies.movies.data.models.ComicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicLocalDataSource {
    @Query("SELECT * FROM comic")
    suspend fun getAll(): List<ComicEntity>

    @Query("SELECT * FROM comic WHERE id=:comicId")
    fun getComicById(comicId: String): Flow<ComicEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllComics(comicList: List<ComicEntity>)

    @Query("UPDATE comic SET isFavorite=:isFavorite WHERE id=:comicId")
    suspend fun setFavoriteComic(comicId: String, isFavorite: Boolean)

    @Query("SELECT * FROM comic WHERE isFavorite=true")
    suspend fun getFavoriteComics(): List<ComicEntity>
}
