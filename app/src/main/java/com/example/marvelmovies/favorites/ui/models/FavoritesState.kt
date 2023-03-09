package com.example.marvelmovies.favorites.ui.models

import com.example.marvelmovies.comics.ui.models.ComicModel

sealed class FavoritesState {
    data class Success(val data: List<ComicModel>) : FavoritesState()
    data class Error(var exception: Throwable) : FavoritesState()
    object Loading : FavoritesState()
}
