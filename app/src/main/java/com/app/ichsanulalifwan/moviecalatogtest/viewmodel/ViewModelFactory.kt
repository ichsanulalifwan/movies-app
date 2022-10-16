package com.app.ichsanulalifwan.moviecalatogtest.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.ichsanulalifwan.moviecalatogtest.data.AppRepository
import com.app.ichsanulalifwan.moviecalatogtest.di.Injection
import com.app.ichsanulalifwan.moviecalatogtest.ui.detail.DetailMovieViewModel
import com.app.ichsanulalifwan.moviecalatogtest.ui.detail.DetailTvShowViewModel
import com.app.ichsanulalifwan.moviecalatogtest.ui.movie.MovieViewModel
import com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow.TvShowViewModel
import com.app.ichsanulalifwan.moviecalatogtest.ui.watchlist.WatchlistViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val repository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(repository) as T
            }

            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(repository) as T
            }

            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(repository) as T
            }

            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) -> {
                DetailTvShowViewModel(repository) as T
            }

            modelClass.isAssignableFrom(WatchlistViewModel::class.java) -> {
            WatchlistViewModel(repository) as T
            }

            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }
}