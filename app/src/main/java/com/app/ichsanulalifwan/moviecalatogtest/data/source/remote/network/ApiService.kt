package com.app.ichsanulalifwan.moviecalatogtest.data.source.remote.network

import com.app.ichsanulalifwan.moviecalatogtest.data.source.remote.response.MovieDetailResponse
import com.app.ichsanulalifwan.moviecalatogtest.data.source.remote.response.MovieResponse
import com.app.ichsanulalifwan.moviecalatogtest.data.source.remote.response.TvShowDetailResponse
import com.app.ichsanulalifwan.moviecalatogtest.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // Movies
    @GET("movie/now_playing")
    fun getNowPlayingMovie(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovie(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovie(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("movie/{id}")
    fun getDetailMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieDetailResponse>


    // TV Shows
    @GET("tv/airing_today")
    fun getAiringTodayTvShow(
        @Query("api_key") apiKey: String
    ): Call<TvShowResponse>

    @GET("tv/on_the_air")
    fun getOnTheAirTvShow(
        @Query("api_key") apiKey: String
    ): Call<TvShowResponse>

    @GET("tv/popular")
    fun getPopularTvShow(
        @Query("api_key") apiKey: String
    ): Call<TvShowResponse>

    @GET("tv/top_rated")
    fun getTopRatedTvShow(
        @Query("api_key") apiKey: String
    ): Call<TvShowResponse>

    @GET("tv/{id}")
    fun getDetailTvShow(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<TvShowDetailResponse>
}