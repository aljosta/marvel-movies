package com.example.marvelmovies.favorites.ui

import android.util.Log
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.marvelmovies.ErrorView
import com.example.marvelmovies.LoadingView
import com.example.marvelmovies.favorites.ui.models.FavoritesState
import com.example.marvelmovies.comics.ui.ComicItemView
import com.example.marvelmovies.comics.ui.EmptyStateView
import com.example.marvelmovies.comics.ui.models.ComicModel

private const val TAG = "FavoriteListView"

@Composable
fun FavoriteListView(
    viewModel: FavoriteListViewModel = hiltViewModel(),
) {
    val favoriteListState by viewModel.favoritesState.collectAsStateWithLifecycle(
        initialValue = FavoritesState.Loading,
    )

    when (favoriteListState) {
        is FavoritesState.Success -> {
            val favoriteList = (favoriteListState as FavoritesState.Success).data
            if (favoriteList.isEmpty()) {
                EmptyStateView()
            } else {
                FavoriteListView(favoriteList = favoriteList)
            }
        }
        is FavoritesState.Error -> {
            val exception = (favoriteListState as FavoritesState.Error).exception
            Log.e(TAG, exception.message.toString())
            ErrorView(exception = exception)
        }
        is FavoritesState.Loading -> {
            LoadingView()
        }
    }
}

@Composable
fun FavoriteListView(
    favoriteList: List<ComicModel>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
    ) {
        items(
            favoriteList.size,
        ) { index ->
            ComicItemView(favoriteList[index])
        }
    }
}
