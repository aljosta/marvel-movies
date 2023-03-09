package com.example.marvelmovies.comics.data.datasources

import com.example.marvelmovies.comics.data.models.ComicListWrapperResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicRemoteDataSource {

    @GET("v1/public/comics")
    suspend fun getComics(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null,
    ): Response<ComicListWrapperResponse>

    @GET("v1/public/comics/{comicId}")
    suspend fun getComicById(@Path("comicId") comicId: String): Response<ComicListWrapperResponse>
}
