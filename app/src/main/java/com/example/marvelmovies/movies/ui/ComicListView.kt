package com.example.marvelmovies.movies.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.marvelmovies.ErrorSnackbarView
import com.example.marvelmovies.ErrorView
import com.example.marvelmovies.LoadingView
import com.example.marvelmovies.R
import com.example.marvelmovies.TopBar
import com.example.marvelmovies.movies.ui.models.ComicModel
import com.example.marvelmovies.ui.views.BottomNavigationBarView
import com.example.marvelmovies.utils.value

private const val TAG = "ComicsView"

@Composable
fun ComicListView(
    navigateToDetail: (String) -> Unit,
    viewModel: ComicListViewModel = hiltViewModel(),
) {
    val productListLazy = viewModel.comicListState.collectAsLazyPagingItems()
    ComicListView(comicListLazy = productListLazy, onItemClick = navigateToDetail)
}

@Composable
fun ComicListView(
    comicListLazy: LazyPagingItems<ComicModel>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
    ) {
        items(
            comicListLazy.itemCount,
        ) { index ->
            ComicItemView(comicListLazy[index]!!, onItemClick)
        }

        when (comicListLazy.loadState.append) {
            is LoadState.Loading -> item { LoadingView() }
            is LoadState.Error -> item {
                ErrorSnackbarView { comicListLazy.retry() }
            }
            else -> {}
        }
    }

    when (comicListLazy.loadState.refresh) {
        is LoadState.Loading -> {
            LoadingView()
        }
        is LoadState.Error -> {
            val exception = (comicListLazy.loadState.refresh as LoadState.Error).error
            Log.e(TAG, exception.localizedMessage.value())
            ErrorView(exception = exception) { comicListLazy.retry() }
        }
        else -> if (comicListLazy.itemCount == 0) { EmptyStateView() }
    } }

@Preview(showBackground = true)
@Composable
fun PreviewProductList() {
    ComicItemView(
        comic = productModelDummy(),
        onItemClick = {},
    )
}

private fun productModelDummy() =
    ComicModel(
        1000,
        "Samsung Galaxy S20 Fe Dual Sim 256 Gb Cloud Mint 8 Gb Ram",
        "http://http2.mlstatic.com/D_651045-MCO51992872179_102022-O.jpg",
    )
