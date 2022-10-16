package com.app.ichsanulalifwan.moviecalatogtest.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.*
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.*

interface AppDataSource {

    // Movies
    fun getNowPlayingMovie(): LiveData<Resource<PagedList<MovieNowPlayingEntity>>>

    fun getPopularMovie(): LiveData<Resource<PagedList<MoviePopularEntity>>>

    fun getTopRatedMovie(): LiveData<Resource<PagedList<MovieTopRatedEntity>>>

    fun getUpcomingMovie(): LiveData<Resource<PagedList<MovieUpcomingEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieDetailWithGenre>>

    fun getWishlistMovie(): LiveData<PagedList<MovieNowPlayingEntity>>

    fun setWishlistMovie(movie: MovieNowPlayingEntity, state: Boolean)


    // TV Shows
    fun getAiringTodayTvShow(): LiveData<Resource<PagedList<TvShowAiringEntity>>>

    fun getOnTheAirTvShow(): LiveData<Resource<PagedList<TvShowOnTheAirEntity>>>

    fun getPopularTvShow(): LiveData<Resource<PagedList<TvShowPopularEntity>>>

    fun getTopRatedTvShow(): LiveData<Resource<PagedList<TvShowTopRatedEntity>>>

    fun getDetailTvShow(tvId: Int): LiveData<Resource<TvDetailWithGenre>>

    fun getWishlistTvShow(): LiveData<PagedList<TvShowAiringEntity>>

    fun setWishlistTvShow(tv: TvShowAiringEntity, state: Boolean)
}