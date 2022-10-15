package com.app.ichsanulalifwan.moviecalatogtest.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieDetailWithGenre
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieNowPlayingEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieGenreEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MoviePopularEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvDetailWithGenre
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvGenreEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowEntity

@Dao
interface AppDao {

    // Movie
    @Query("SELECT * FROM movie_nowplaying_entities")
    fun getNowPlayingMovies(): DataSource.Factory<Int, MovieNowPlayingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNowPlayingMovie(movie: List<MovieNowPlayingEntity>)

    @Query("SELECT * FROM movie_popular_entities")
    fun getPopularMovies(): DataSource.Factory<Int, MoviePopularEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovie(movie: List<MoviePopularEntity>)


    @Query("SELECT * FROM movie_nowplaying_entities where isWishlist = 1")
    fun getWishlistMovie(): DataSource.Factory<Int, MovieNowPlayingEntity>

    @Transaction
    @Query("SELECT * FROM movie_nowplaying_entities WHERE movieId = :movieId")
    fun getDetailMovieById(movieId: Int): LiveData<MovieDetailWithGenre>

    @Update
    fun updateMovie(movie: MovieNowPlayingEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovieGenre(genre: List<MovieGenreEntity>)


    // Tv Show
    @Query("SELECT * FROM tv_entities")
    fun getTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tv_entities where isWishlist = 1")
    fun getWishlistTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Transaction
    @Query("SELECT * FROM tv_entities WHERE tvId = :tvId")
    fun getDetailTvById(tvId: Int): LiveData<TvDetailWithGenre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tv: List<TvShowEntity>)

    @Update
    fun updateTvShow(tv: TvShowEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTvGenre(genre: List<TvGenreEntity>)
}