package com.example.marvelmovies.comicdetail.domain.usecases

import com.example.marvelmovies.comics.data.repositories.ComicDataRepository
import com.example.marvelmovies.comics.domain.mapper.ComicDataToDtoMapper
import com.example.marvelmovies.comics.domain.models.ComicDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetComicByIdUseCase @Inject constructor(
    private val repository: ComicDataRepository,
) {
    suspend fun execute(comicId: String): Flow<ComicDto> =
        repository.getComicById(comicId).map(ComicDataToDtoMapper::transform)
}
