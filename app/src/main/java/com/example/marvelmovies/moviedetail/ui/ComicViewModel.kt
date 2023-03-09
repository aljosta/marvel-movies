package com.example.marvelmovies.moviedetail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelmovies.moviedetail.domain.usecases.GetComicByIdUseCase
import com.example.marvelmovies.favorites.domain.SetFavoriteComicUseCase
import com.example.marvelmovies.moviedetail.ui.models.ComicDetailState
import com.example.marvelmovies.movies.ui.mapper.ComicDtoToModelMapper
import com.example.marvelmovies.ui.NavArgs
import com.example.marvelmovies.utils.value
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val getComicByIdUseCase: GetComicByIdUseCase,
    private val setFavoriteUseCase: SetFavoriteComicUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val comicId: String = savedStateHandle.get<String>(NavArgs.ComicId.key).value()

    private val _comicDetailState = MutableStateFlow<ComicDetailState>(ComicDetailState.Loading)
    val comicDetailState: StateFlow<ComicDetailState>
        get() = _comicDetailState

    init {
        getComicDetail(comicId)
    }

    private fun getComicDetail(comicId: String) {
        viewModelScope.launch {
            try {
                getComicByIdUseCase.execute(comicId)
                    .map(ComicDtoToModelMapper::transform)
                    .collectLatest { comicCollected ->
                        _comicDetailState.value = ComicDetailState.Success(comicCollected)
                    }
            } catch (exception: Exception) {
                _comicDetailState.value = ComicDetailState.Error(exception)
            }
        }
    }

    fun toggleComicFavorite(comicId: String, isFavorite: Boolean) {
        viewModelScope.launch {
            try {
                setFavoriteUseCase.execute(comicId, !isFavorite)
            } catch (exception: Exception) {
                println("exception")
            }
        }
    }
}
