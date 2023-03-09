package com.example.marvelmovies.comics.domain

import androidx.paging.PagingData
import com.example.marvelmovies.comics.data.models.ComicEntity
import kotlinx.coroutines.flow.Flow

interface ComicRepository {
    fun getComics(): Flow<PagingData<ComicEntity>>
    suspend fun getComicById(comicId: String): Flow<ComicEntity>
    suspend fun setFavoriteComic(comicId: String, isFavorite: Boolean)
    suspend fun getFavoriteComics(): List<ComicEntity>
}
