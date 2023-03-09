package com.example.marvelmovies.comicdetail.ui.models

import com.example.marvelmovies.comics.ui.models.ComicModel

sealed class ComicDetailState {
    data class Success(val data: ComicModel) : ComicDetailState()
    data class Error(var exception: Throwable) : ComicDetailState()
    object Loading : ComicDetailState()
}