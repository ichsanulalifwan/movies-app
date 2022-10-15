package com.app.ichsanulalifwan.moviecalatogtest.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieDetailWithGenre
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvDetailWithGenre
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowEntity

interface AppDataSource {

    // Movies
    fun getNowPlayingMovie(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getPopularMovie(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTopRatedMovie(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getUpcomingMovie(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<Resource<MovieDetailWithGenre>>

    fun getWishlistMovie(): LiveData<PagedList<MovieEntity>>

    fun setWishlistMovie(movie: MovieEntity, state: Boolean)


    // TV Shows
    fun getAiringTodayTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getOnTheAirTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getPopularTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getTopRatedTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailTvShow(tvId: Int): LiveData<Resource<TvDetailWithGenre>>

    fun getWishlistTvShow(): LiveData<PagedList<TvShowEntity>>

    fun setWishlistTvShow(tv: TvShowEntity, state: Boolean)
}