package com.app.ichsanulalifwan.moviecalatogtest.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.app.ichsanulalifwan.moviecalatogtest.data.AppRepository
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieDetailWithGenre

class DetailMovieViewModel(private val repository: AppRepository) : ViewModel() {

    val movieId = MutableLiveData<Int>()

    var detailMovie: LiveData<Resource<MovieDetailWithGenre>> =
        Transformations.switchMap(movieId) { movieId ->
            repository.getDetailMovie(movieId)
        }

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    fun setMovieFavorite() {
        val movieResource = detailMovie.value
        if (movieResource != null) {
            val movie = movieResource.data
            if (movie != null) {
                val movieEntity = movie.mMovie
                val newState = !movieEntity.isWishlist
                repository.setWishlistMovie(movieEntity, newState)
            }
        }
    }
}