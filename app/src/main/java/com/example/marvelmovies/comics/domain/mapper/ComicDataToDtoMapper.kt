package com.example.marvelmovies.comics.domain.mapper

import com.example.marvelmovies.comics.data.models.ComicEntity
import com.example.marvelmovies.comics.data.models.ThumbnailEntity
import com.example.marvelmovies.comics.domain.models.ComicDto
import com.example.marvelmovies.comics.domain.models.ThumbnailDto
import com.example.marvelmovies.utils.value

object ComicDataToDtoMapper {

    fun transform(comic: ComicEntity) =
        with(comic) {
            ComicDto(
                id = id.value(),
                title = title.value(),
                thumbnail = thumbnailDataToDto(thumbnail ?: ThumbnailEntity()),
                isFavorite = isFavorite.value(),
            )
        }

    private fun thumbnailDataToDto(thumbnail: ThumbnailEntity) =
        ThumbnailDto(path = thumbnail.path + "." + thumbnail.extension)
}
