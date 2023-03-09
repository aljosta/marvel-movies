package com.example.marvelmovies.comicdetail.data.models

import com.example.marvelmovies.comics.data.models.ComicEntity

data class ComicContainerResponse(
    val results: List<ComicEntity> = arrayListOf(),
)
