package com.example.marvelmovies.comics.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comic")
data class ComicEntity(
    @PrimaryKey
    val id: Int? = null,
    val title: String? = null,
    @Embedded val thumbnail: ThumbnailEntity? = null,
    val isFavorite: Boolean? = null,
)
