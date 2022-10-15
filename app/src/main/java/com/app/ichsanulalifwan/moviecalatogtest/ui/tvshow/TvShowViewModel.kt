package com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.app.ichsanulalifwan.moviecalatogtest.data.AppRepository
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowAiringEntity

class TvShowViewModel(private val repository: AppRepository) : ViewModel() {

    fun getAiringTodayTvShow(): LiveData<Resource<PagedList<TvShowAiringEntity>>> = repository.getAiringTodayTvShow()

    fun getOnTheAirTvShow(): LiveData<Resource<PagedList<TvShowAiringEntity>>> = repository.getOnTheAirTvShow()

    fun getPopularTvShow(): LiveData<Resource<PagedList<TvShowAiringEntity>>> = repository.getPopularTvShow()

    fun getTopRatedTvShow(): LiveData<Resource<PagedList<TvShowAiringEntity>>> = repository.getTopRatedTvShow()
}