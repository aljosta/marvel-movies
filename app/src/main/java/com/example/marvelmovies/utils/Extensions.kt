package com.example.marvelmovies.utils

import android.widget.ImageView
import coil.load
import coil.request.ImageRequest

fun String?.value(default: String = "") = this ?: default

fun <T> List<T>?.value(default: List<T> = emptyList()) = this ?: default

fun Int?.value(default: Int = 0) = this ?: default

fun Boolean?.value(default: Boolean = false) = this ?: default

fun Double?.value(default: Double = 0.0) = this ?: default

fun ImageView.loadSvgFromUrl(image: String, builder: ImageRequest.Builder.() -> Unit = {}) =
    load(image) {
        builder()
    }