package com.app.ichsanulalifwan.moviecalatogtest.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.app.ichsanulalifwan.moviecalatogtest.data.AppRepository
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieEntity

class MovieViewModel(private val repository: AppRepository) : ViewModel() {

    fun getNowPlayingMovie(): LiveData<Resource<PagedList<MovieEntity>>> = repository.getNowPlayingMovie()

    fun getPopularMovie(): LiveData<Resource<PagedList<MovieEntity>>> = repository.getPopularMovie()

    fun getTopRatedMovie(): LiveData<Resource<PagedList<MovieEntity>>> = repository.getTopRatedMovie()

    fun getUpcomingMovie(): LiveData<Resource<PagedList<MovieEntity>>> = repository.getUpcomingMovie()
}