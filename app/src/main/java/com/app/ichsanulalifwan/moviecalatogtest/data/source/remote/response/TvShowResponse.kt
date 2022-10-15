package com.app.ichsanulalifwan.moviecalatogtest.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

    @field:SerializedName("results")
    val results: List<TvShowResultsItem>
)

data class TvShowResultsItem(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("poster_path")
    val posterPath: String
)
