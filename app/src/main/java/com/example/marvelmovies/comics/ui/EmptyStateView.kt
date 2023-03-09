package com.example.marvelmovies.comics.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marvelmovies.R
import com.example.marvelmovies.utils.Constants

@Composable
fun EmptyStateView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_round_search),
            contentDescription = Constants.EMPTY_STRING,
            modifier = Modifier.size(60.dp, 60.dp),
        )
        Text(
            text = stringResource(id = R.string.empty_state_message_title),
            modifier = Modifier.padding(bottom = 5.dp),
            fontSize = 18.sp,
        )
        Text(
            text = stringResource(id = R.string.empty_state_message_description),
            fontSize = 16.sp,
        )
    }
}