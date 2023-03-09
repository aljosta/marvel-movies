package com.example.marvelmovies.comics.ui.mapper

import com.example.marvelmovies.comics.domain.models.ComicDto
import com.example.marvelmovies.comics.ui.models.ComicModel
import com.example.marvelmovies.utils.value

object ComicDtoToModelMapper {
    fun transform(comic: ComicDto) =
        with(comic) {
            ComicModel(
                id = id.value(),
                title = title.value(),
                thumbnail = thumbnail.path,
                isFavorite = isFavorite,
            )
        }
}
