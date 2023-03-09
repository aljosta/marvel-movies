package com.example.marvelmovies.comics.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.marvelmovies.comics.domain.usecases.GetComicsUseCase
import com.example.marvelmovies.comics.ui.mapper.ComicDtoToModelMapper
import com.example.marvelmovies.comics.ui.models.ComicModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ComicListViewModel @Inject constructor(
    private val getComicsUseCase: GetComicsUseCase,
) : ViewModel() {
    private var _comicListState = getComicList()
    val comicListState: Flow<PagingData<ComicModel>>
        get() = _comicListState

    private fun getComicList() =
        getComicsUseCase.execute().map { pagingData ->
            pagingData.map(ComicDtoToModelMapper::transform)
        }.cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)
}
