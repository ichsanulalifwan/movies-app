package com.app.ichsanulalifwan.moviecalatogtest.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.app.ichsanulalifwan.moviecalatogtest.data.AppRepository
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieNowPlayingEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowAiringEntity

class WatchlistViewModel(private val repository: AppRepository) : ViewModel() {

    fun getWatchlistMovie(): LiveData<PagedList<MovieNowPlayingEntity>> = repository.getWishlistMovie()

    fun getWatchlistTvShow(): LiveData<PagedList<TvShowAiringEntity>> = repository.getWishlistTvShow()
}