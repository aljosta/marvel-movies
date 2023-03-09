package com.example.marvelmovies.moviedetail.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.marvelmovies.ErrorView
import com.example.marvelmovies.LoadingView
import com.example.marvelmovies.R
import com.example.marvelmovies.TopBar
import com.example.marvelmovies.moviedetail.ui.models.ComicDetailState
import com.example.marvelmovies.movies.ui.models.ComicModel
import com.example.marvelmovies.utils.Constants

private const val TAG = "ComicDetailView"

@Composable
fun ComicView(
    viewModel: ComicViewModel = hiltViewModel(),
) {
    val comicState by viewModel.comicDetailState.collectAsStateWithLifecycle(
        initialValue = ComicDetailState.Loading,
    )

    when (comicState) {
        is ComicDetailState.Success -> {
            val comicDetail = (comicState as ComicDetailState.Success).data
            ComicDetailView(
                comicDetail = comicDetail,
                isFavorite = comicDetail.isFavorite,
                onCheckedChange = {
                    viewModel.toggleComicFavorite(
                        comicDetail.id.toString(),
                        comicDetail.isFavorite,
                    )
                },
                modifier = Modifier.verticalScroll(rememberScrollState())
                    .fillMaxWidth(),
            )
        }
        is ComicDetailState.Error -> {
            val exception = (comicState as ComicDetailState.Error).exception
            Log.e(TAG, exception.message.toString())
            ErrorView(exception)
        }
        is ComicDetailState.Loading -> {
            LoadingView()
        }
    }

}

@Composable
fun ComicDetailView(
    comicDetail: ComicModel,
    isFavorite: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        AsyncImage(
            model = comicDetail.thumbnail,
            contentDescription = Constants.EMPTY_STRING,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize(),
        )
        Text(
            text = comicDetail.title,
            modifier = Modifier.padding(bottom = 5.dp),
            lineHeight = 17.sp,
        )

        FavoriteButton(
            isFavorite = isFavorite,
            onCheckedChange = onCheckedChange,
        )
    }
}

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = Color(0xffE91E63),
) {
    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = onCheckedChange,
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null,
        )
    }
}
