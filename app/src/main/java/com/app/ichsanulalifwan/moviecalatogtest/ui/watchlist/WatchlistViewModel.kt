package com.app.ichsanulalifwan.moviecalatogtest.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.app.ichsanulalifwan.moviecalatogtest.data.AppRepository
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowEntity

class WatchlistViewModel(private val repository: AppRepository) : ViewModel() {

    fun getWishlistMovie(): LiveData<PagedList<MovieEntity>> = repository.getWishlistMovie()

    fun getWishlistTvShow(): LiveData<PagedList<TvShowEntity>> = repository.getWishlistTvShow()
}