package com.example.marvelmovies.movies.domain.models

class ComicDto(
    val id: Int,
    val title: String,
    val thumbnail: ThumbnailDto,
    val isFavorite: Boolean = false,
)
