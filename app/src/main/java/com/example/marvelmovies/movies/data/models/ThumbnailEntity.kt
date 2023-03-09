package com.example.marvelmovies.movies.data.models

import androidx.room.Entity

@Entity(tableName = "thumbnail")
data class ThumbnailEntity(
    val path: String? = null,
    val extension: String? = null,
)
