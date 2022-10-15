package com.app.ichsanulalifwan.moviecalatogtest.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.*
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.*

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

    @Query("SELECT * FROM movie_toprated_entities")
    fun getTopRatedMovies(): DataSource.Factory<Int, MovieTopRatedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRatedMovie(movie: List<MovieTopRatedEntity>)

    @Query("SELECT * FROM movie_upcoming_entities")
    fun getUpcomingMovies(): DataSource.Factory<Int, MovieUpcomingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingMovie(movie: List<MovieUpcomingEntity>)


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
    @Query("SELECT * FROM tv_airing_entities")
    fun getAiringTvShow(): DataSource.Factory<Int, TvShowAiringEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAiringTvShow(tv: List<TvShowAiringEntity>)

    @Query("SELECT * FROM tv_on_the_air_entities")
    fun getOnTheAirTvShow(): DataSource.Factory<Int, TvShowOnTheAirEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOnTheAirTvShow(tv: List<TvShowOnTheAirEntity>)

    @Query("SELECT * FROM tv_popular_entities")
    fun getPopularTvShow(): DataSource.Factory<Int, TvShowPopularEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularTvShow(tv: List<TvShowPopularEntity>)

    @Query("SELECT * FROM tv_top_rated_entities")
    fun getTopRatedTvShow(): DataSource.Factory<Int, TvShowTopRatedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopRatedTvShow(tv: List<TvShowTopRatedEntity>)


    @Query("SELECT * FROM tv_airing_entities where isWishlist = 1")
    fun getWishlistTvShow(): DataSource.Factory<Int, TvShowAiringEntity>

    @Transaction
    @Query("SELECT * FROM tv_airing_entities WHERE tvId = :tvId")
    fun getDetailTvById(tvId: Int): LiveData<TvDetailWithGenre>

    @Update
    fun updateTvShow(tv: TvShowAiringEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTvGenre(genre: List<TvGenreEntity>)
}