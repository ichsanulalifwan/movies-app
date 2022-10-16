package com.app.ichsanulalifwan.moviecalatogtest.utils

import com.app.ichsanulalifwan.moviecalatogtest.data.model.GenreData
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieGenreEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvGenreEntity

object DataMapper {

    fun mapMovieGenreEntityToModel(input: List<MovieGenreEntity>): List<GenreData> =
        input.map {
            GenreData(
                name = it.genreName,
                id = it.genreId
            )
        }

    fun mapTvGenreEntityToModel(input: List<TvGenreEntity>): List<GenreData> =
        input.map {
            GenreData(
                name = it.genreName,
                id = it.genreId
            )
        }
}