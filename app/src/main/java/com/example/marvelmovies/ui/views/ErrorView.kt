package com.example.marvelmovies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marvelmovies.utils.Constants

@Composable
fun ErrorView(
    exception: Throwable,
    onRetry: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_cloud_off),
            contentDescription = Constants.EMPTY_STRING,
            modifier = Modifier.size(60.dp, 60.dp),
        )
        Text(
            text = stringResource(id = R.string.general_error_message),
            modifier = Modifier.padding(bottom = 5.dp),
            fontSize = 18.sp,
        )
        Text(text = exception.message.toString())
        onRetry?.let {
            OutlinedButton(onClick = onRetry) {
                Text(text = stringResource(id = R.string.retry_button_text))
            }
        }
    }
}

@Composable
fun ErrorSnackbarView(
    onRetry: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Snackbar(
            action = {
                Button(onClick = onRetry) {
                    Text(text = stringResource(id = R.string.retry_button_text))
                }
            },
            modifier = Modifier.padding(8.dp),
        ) {
            Text(text = stringResource(id = R.string.general_error_message))
        }
    }
}
