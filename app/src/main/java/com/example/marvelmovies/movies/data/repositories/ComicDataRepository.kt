package com.example.marvelmovies.movies.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.marvelmovies.movies.data.datasources.ComicLocalDataSource
import com.example.marvelmovies.movies.data.datasources.ComicPagingSource
import com.example.marvelmovies.movies.data.datasources.ComicRemoteDataSource
import com.example.marvelmovies.movies.data.models.ComicEntity
import com.example.marvelmovies.movies.domain.ComicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override suspend fun getComicById(comicId: String): Flow<ComicEntity> {
        val localComicResponse = comicLocalDataSource.getComicById(comicId)
        return localComicResponse
        /* if (localComicResponse.lastOrNull() != null) {
            return localComicResponse
        }

        val comicResponse = comicRemoteDataSource.getComicById(comicId = comicId)

        if (!comicResponse.isSuccessful) {
            throw Exception()
        }

        return flow {
            emit(comicResponse.body()?.data?.results?.get(0) ?: ComicEntity())
        } */
    }

    override suspend fun setFavoriteComic(
        comicId: String,
        isFavorite: Boolean,
    ) {
        comicLocalDataSource.setFavoriteComic(comicId, isFavorite)
    }

    override suspend fun getFavoriteComics(): List<ComicEntity> =
        comicLocalDataSource.getFavoriteComics()

    companion object {
        private const val PAGE_SIZE = 20
        private const val PREFETCH_DISTANCE = 5
    }
}
