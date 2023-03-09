package com.example.marvelmovies.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelmovies.favorites.domain.GetFavoriteComics
import com.example.marvelmovies.favorites.ui.models.FavoritesState
import com.example.marvelmovies.comics.ui.mapper.ComicDtoToModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel @Inject constructor(
    private val getFavoriteComics: GetFavoriteComics,
) : ViewModel() {

    private val _favoritesState = MutableStateFlow<FavoritesState>(FavoritesState.Loading)
    val favoritesState: StateFlow<FavoritesState>
        get() = _favoritesState

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            try {
                _favoritesState.value = FavoritesState.Success(
                    getFavoriteComics.execute().map(ComicDtoToModelMapper::transform),
                )
            } catch (exception: Exception) {
                _favoritesState.value = FavoritesState.Error(exception)
            }
        }
    }
}
