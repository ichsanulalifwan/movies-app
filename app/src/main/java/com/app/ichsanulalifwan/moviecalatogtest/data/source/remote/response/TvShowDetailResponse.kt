package com.app.ichsanulalifwan.moviecalatogtest.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowDetailResponse(

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("number_of_seasons")
    val numberOfSeasons: Int,
)
