package com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_entities")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvId")
    var tvId: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "poster_path")
    var posterPath: String,

    @ColumnInfo(name = "number_of_episodes")
    var numberOfEpisodes: Int? = 0,

    @ColumnInfo(name = "number_of_seasons")
    var numberOfSeasons: Int? = 0,

    @ColumnInfo(name = "isWishlist")
    var isWishlist: Boolean = false,
)
