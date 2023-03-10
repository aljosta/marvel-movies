package com.example.marvelmovies.comics.data.models

data class ComicListContainerResponse(
    val offset: Int? = null,
    val limit: Int? = null,
    val total: Int? = null,
    val results: List<ComicEntity> = arrayListOf(),
)
