package com.app.ichsanulalifwan.moviecalatogtest.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.app.ichsanulalifwan.moviecalatogtest.data.AppRepository
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieNowPlayingEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MoviePopularEntity

class MovieViewModel(private val repository: AppRepository) : ViewModel() {

    fun getNowPlayingMovie(): LiveData<Resource<PagedList<MovieNowPlayingEntity>>> = repository.getNowPlayingMovie()

    fun getPopularMovie(): LiveData<Resource<PagedList<MoviePopularEntity>>> = repository.getPopularMovie()

    fun getTopRatedMovie(): LiveData<Resource<PagedList<MovieNowPlayingEntity>>> = repository.getTopRatedMovie()

    fun getUpcomingMovie(): LiveData<Resource<PagedList<MovieNowPlayingEntity>>> = repository.getUpcomingMovie()
}