package com.example.marvelmovies.moviedetail.ui.models

import com.example.marvelmovies.movies.ui.models.ComicModel

sealed class ComicDetailState {
    data class Success(val data: ComicModel) : ComicDetailState()
    data class Error(var exception: Throwable) : ComicDetailState()
    object Loading : ComicDetailState()
}