package com.example.marvelmovies.comics.domain.models

class ComicDto(
    val id: Int,
    val title: String,
    val thumbnail: ThumbnailDto,
    val isFavorite: Boolean = false,
)
