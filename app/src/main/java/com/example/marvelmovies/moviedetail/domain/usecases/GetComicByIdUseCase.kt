package com.example.marvelmovies.moviedetail.domain.usecases

import com.example.marvelmovies.movies.data.repositories.ComicDataRepository
import com.example.marvelmovies.movies.domain.mapper.ComicDataToDtoMapper
import com.example.marvelmovies.movies.domain.models.ComicDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetComicByIdUseCase @Inject constructor(
    private val repository: ComicDataRepository,
) {
    suspend fun execute(comicId: String): Flow<ComicDto> =
        repository.getComicById(comicId).map(ComicDataToDtoMapper::transform)
}
