package com.example.marvelmovies.favorites.domain

import com.example.marvelmovies.comics.data.repositories.ComicDataRepository
import javax.inject.Inject

class SetFavoriteComicUseCase @Inject constructor(
    private val repository: ComicDataRepository,
) {
    suspend fun execute(
        comicId: String,
        isFavorite: Boolean,
    ) = repository.setFavoriteComic(comicId, isFavorite)
}
