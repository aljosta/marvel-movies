package com.example.marvelmovies.comics.domain.usecases

import androidx.paging.PagingData
import androidx.paging.map
import com.example.marvelmovies.comics.domain.ComicRepository
import com.example.marvelmovies.comics.domain.mapper.ComicDataToDtoMapper
import com.example.marvelmovies.comics.domain.models.ComicDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetComicsUseCase @Inject constructor(
    private val repository: ComicRepository
) {
    fun execute(): Flow<PagingData<ComicDto>> =
        repository.getComics().map { pagingData ->
            pagingData.map(ComicDataToDtoMapper::transform)
        }
}
