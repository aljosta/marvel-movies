package com.example.marvelmovies.movies.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.marvelmovies.movies.ui.models.ComicModel
import com.example.marvelmovies.utils.Constants

@Composable
fun ComicItemView(
    comic: ComicModel,
    onItemClick: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .clickable { onItemClick(comic.id.toString()) }
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        AsyncImage(
            model = comic.thumbnail,
            contentDescription = Constants.EMPTY_STRING,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize(),
        )
        Text(
            text = comic.title,
            modifier = Modifier.padding(bottom = 5.dp),
            lineHeight = 17.sp,
        )
    }
    Divider()
}
