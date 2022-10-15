package com.app.ichsanulalifwan.moviecalatogtest.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieDetailWithGenre
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieNowPlayingEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieGenreEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MoviePopularEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvDetailWithGenre
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvGenreEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.room.AppDao

class LocalDataSource private constructor(private val appDao: AppDao) {

    // Movie
    fun getNowPlayingMovie(): DataSource.Factory<Int, MovieNowPlayingEntity> = appDao.getNowPlayingMovies()

    fun insertNowPlayingMovie(movie: List<MovieNowPlayingEntity>) = appDao.insertNowPlayingMovie(movie)

    fun getPopularMovie(): DataSource.Factory<Int, MoviePopularEntity> = appDao.getPopularMovies()

    fun insertPopularMovie(movie: List<MoviePopularEntity>) = appDao.insertPopularMovie(movie)


    fun getWishlistMovie(): DataSource.Factory<Int, MovieNowPlayingEntity> = appDao.getWishlistMovie()

    fun getDetailMovieById(movieId: Int): LiveData<MovieDetailWithGenre> = appDao.getDetailMovieById(movieId)

    fun setWishlistMovie(movie: MovieNowPlayingEntity, newState: Boolean) {
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