package com.example.marvelmovies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marvelmovies.utils.Constants

@Composable
fun TopBar(
    title: String,
    icon: ImageVector,
    onIconClick: (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            Icon(
                imageVector = icon,
                contentDescription = Constants.EMPTY_STRING,
                modifier = Modifier.padding(start = 5.dp)
                    .size(35.dp)
                    .clickable { onIconClick?.run { invoke() } },
            )
        },
    )
}

@Preview
@Composable
fun PreviewTopAppBar() {
    TopBar("Pruebas", Icons.Default.Home)
}
