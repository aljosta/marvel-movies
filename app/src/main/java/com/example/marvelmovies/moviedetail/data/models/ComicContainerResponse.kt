package com.example.marvelmovies.moviedetail.data.models

import com.example.marvelmovies.movies.data.models.ComicEntity

data class ComicContainerResponse(
    val results: List<ComicEntity> = arrayListOf(),
)
