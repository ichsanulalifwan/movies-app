package com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.app.ichsanulalifwan.moviecalatogtest.data.AppRepository
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowEntity

class TvShowViewModel(private val repository: AppRepository) : ViewModel() {

    fun getAiringTodayTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> = repository.getAiringTodayTvShow()

    fun getOnTheAirTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> = repository.getOnTheAirTvShow()

    fun getPopularTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> = repository.getPopularTvShow()

    fun getTopRatedTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> = repository.getTopRatedTvShow()
}