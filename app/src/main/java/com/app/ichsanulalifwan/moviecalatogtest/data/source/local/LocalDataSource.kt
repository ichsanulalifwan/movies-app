package com.app.ichsanulalifwan.moviecalatogtest.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieDetailWithGenre
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieGenreEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvDetailWithGenre
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvGenreEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.room.AppDao

class LocalDataSource private constructor(private val appDao: AppDao) {

    // Movie
    fun getPopularMovie(): DataSource.Factory<Int, MovieEntity> = appDao.getMovies()

    fun getWishlistMovie(): DataSource.Factory<Int, MovieEntity> = appDao.getWishlistMovie()

    fun getDetailMovieById(movieId: Int): LiveData<MovieDetailWithGenre> = appDao.getDetailMovieById(movieId)

    fun insertMovie(movie: List<MovieEntity>) = appDao.insertMovie(movie)

    fun setWishlistMovie(movie: MovieEntity, newState: Boolean) {
        movie.isWishlist = newState
        appDao.updateMovie(movie)
    }

    fun insertMovieGenre(genres: List<MovieGenreEntity>) = appDao.insertMovieGenre(genres)


    // Tv Show
    fun getPopularTvShow(): DataSource.Factory<Int, TvShowEntity> = appDao.getTvShow()

    fun getWishlistTvShow(): DataSource.Factory<Int, TvShowEntity> = appDao.getWishlistTvShow()

    fun getDetailTvById(tvId: Int): LiveData<TvDetailWithGenre> = appDao.getDetailTvById(tvId)

    fun insertTvShow(tvShow: List<TvShowEntity>) = appDao.insertTvShow(tvShow)

    fun setWishlistTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isWishlist = newState
        appDao.updateTvShow(tvShow)
    }

    fun insertTvShowGenre(genres: List<TvGenreEntity>) = appDao.insertTvGenre(genres)

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(appDao: AppDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(appDao)
    }
}