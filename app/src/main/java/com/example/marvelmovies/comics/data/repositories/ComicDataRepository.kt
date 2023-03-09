package com.example.marvelmovies.comics.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.marvelmovies.comics.data.datasources.ComicLocalDataSource
import com.example.marvelmovies.comics.data.datasources.ComicPagingSource
import com.example.marvelmovies.comics.data.datasources.ComicRemoteDataSource
import com.example.marvelmovies.comics.data.models.ComicEntity
import com.example.marvelmovies.comics.domain.ComicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ComicDataRepository @Inject constructor(
    private val comicRemoteDataSource: ComicRemoteDataSource,
    private val comicLocalDataSource: ComicLocalDataSource,
) : ComicRepository {
    override fun getComics(): Flow<PagingData<ComicEntity>> =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                enablePlaceholders = true,
            ),
            pagingSourceFactory = {
                ComicPagingSource(
                    comicRemoteDataSource,
                    comicLocalDataSource,
                    PAGE_SIZE,
                )
            },
        ).flow

    override suspend fun getComicById(comicId: String): Flow<ComicEntity> =
        comicLocalDataSource.getComicById(comicId)

    override suspend fun setFavoriteComic(
        comicId: String,
        isFavorite: Boolean,
    ) {
        comicLocalDataSource.setFavoriteComic(comicId, isFavorite)
    }

    override suspend fun getFavoriteComics(): List<ComicEntity> =
        comicLocalDataSource.getFavoriteComics()

    companion object {
        private const val PAGE_SIZE = 40
        private const val PREFETCH_DISTANCE = 4
    }
}
