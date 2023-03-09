package com.example.marvelmovies.comics.ui.models

data class ComicModel(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val isFavorite: Boolean = false,
)
