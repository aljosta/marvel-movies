package com.example.marvelmovies.comics.data.datasources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelmovies.comics.data.models.ComicEntity
import com.example.marvelmovies.comics.data.models.ComicListContainerResponse
import com.example.marvelmovies.comics.data.models.ComicListWrapperResponse
import com.example.marvelmovies.utils.value

class ComicPagingSource(
    private val remoteDataSource: ComicRemoteDataSource,
    private val localDataSource: ComicLocalDataSource,
    private val limit: Int,
) : PagingSource<Int, ComicEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ComicEntity> {
        return try {
            val offset = params.key ?: 0
            val comicsResponse = getComicList(offset = offset)
            comicsResponse?.data?.let { page ->
                LoadResult.Page(
                    data = page.results.value(),
                    prevKey = null,
                    nextKey = getNextKey(page, offset),
                )
            } ?: LoadResult.Error(Exception())
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ComicEntity>): Int? = null

    /* private suspend fun getComicList(offset: Int): List<ComicEntity> {
        return localDataSource.getAll().ifEmpty {
            val remoteResponse = remoteDataSource.getComics(offset, limit)
            if (remoteResponse.isSuccessful) {
                val remoteComicList = remoteResponse.body()?.data?.results.value()
                localDataSource.insertAllComics(remoteComicList)
                remoteComicList
            } else {
                throw Exception()
            }
        }
    } */

    private suspend fun getComicList(offset: Int): ComicListWrapperResponse? {
        val localComicList = localDataSource.getAll()
        return if (localComicList.isNotEmpty() && offset == 0) {
            ComicListWrapperResponse(
                data = ComicListContainerResponse(
                    offset = 0,
                    limit = localComicList.size,
                    total = localComicList.size,
                    results = localComicList,
                ),
            )
        } else {
            val remoteResponse = remoteDataSource.getComics(offset, limit)
            if (remoteResponse.isSuccessful) {
                val remoteComicList = remoteResponse.body()?.data?.results.value()
                localDataSource.insertAllComics(remoteComicList)
                remoteResponse.body()
            } else {
                throw Exception()
            }
        }
    }

    private fun getNextKey(
        page: ComicListContainerResponse,
        offset: Int,
    ): Int? {
        val total = offset.plus(limit)
        return if (page.results.isEmpty() || offset.plus(limit) < total) {
            null
        } else {
            offset.plus(limit)
        }
    }
}
