package com.example.marvelmovies.favorites.domain

import com.example.marvelmovies.comics.data.repositories.ComicDataRepository
import com.example.marvelmovies.comics.domain.mapper.ComicDataToDtoMapper
import com.example.marvelmovies.comics.domain.models.ComicDto
import javax.inject.Inject

class GetFavoriteComics @Inject constructor(
    private val repository: ComicDataRepository
) {
    suspend fun execute(): List<ComicDto> =
        repository.getFavoriteComics().map(ComicDataToDtoMapper::transform)
}