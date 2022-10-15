package com.app.ichsanulalifwan.moviecalatogtest.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.ichsanulalifwan.moviecalatogtest.data.source.remote.network.ApiConfig
import com.app.ichsanulalifwan.moviecalatogtest.data.source.remote.network.ApiResponse
import com.app.ichsanulalifwan.moviecalatogtest.data.source.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemoteDataSource {

    // Movies
    fun getNowPlayingMovie(): LiveData<ApiResponse<List<MovieResultsItem>>> {
        val movies = MutableLiveData<ApiResponse<List<MovieResultsItem>>>()
        ApiConfig.getApiService().getNowPlayingMovie(API_KEY).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    movies.value = ApiResponse.Success(response.body()?.results as List<MovieResultsItem>)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return movies
    }

    fun getPopularMovie(): LiveData<ApiResponse<List<MovieResultsItem>>> {
        val movies = MutableLiveData<ApiResponse<List<MovieResultsItem>>>()
        ApiConfig.getApiService().getPopularMovie(API_KEY).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    movies.value = ApiResponse.Success(response.body()?.results as List<MovieResultsItem>)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return movies
    }

    fun getTopRatedMovie(): LiveData<ApiResponse<List<MovieResultsItem>>> {
        val movies = MutableLiveData<ApiResponse<List<MovieResultsItem>>>()
        ApiConfig.getApiService().getTopRatedMovie(API_KEY).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    movies.value = ApiResponse.Success(response.body()?.results as List<MovieResultsItem>)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return movies
    }

    fun getUpcomingMovie(): LiveData<ApiResponse<List<MovieResultsItem>>> {
        val movies = MutableLiveData<ApiResponse<List<MovieResultsItem>>>()
        ApiConfig.getApiService().getUpcomingMovie(API_KEY).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    movies.value = ApiResponse.Success(response.body()?.results as List<MovieResultsItem>)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return movies
    }

    fun getDetailMovie(movieId: Int): LiveData<ApiResponse<MovieDetailResponse>> {
        val detailMovie = MutableLiveData<ApiResponse<MovieDetailResponse>>()
        ApiConfig.getApiService().getDetailMovie(movieId, API_KEY)
            .enqueue(object : Callback<MovieDetailResponse> {
                override fun onResponse(
                    call: Call<MovieDetailResponse>,
                    response: Response<MovieDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        detailMovie.value = ApiResponse.Success(response.body() as MovieDetailResponse)
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })

        return detailMovie
    }


    // TV Shows
    fun getAiringTodayTvShow(): LiveData<ApiResponse<List<TvShowResultsItem>>> {
        val tvShow = MutableLiveData<ApiResponse<List<TvShowResultsItem>>>()
        ApiConfig.getApiService().getAiringTodayTvShow(API_KEY).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    tvShow.value = ApiResponse.Success(response.body()?.results as List<TvShowResultsItem>)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return tvShow
    }

    fun getOnTheAirTvShow(): LiveData<ApiResponse<List<TvShowResultsItem>>> {
        val tvShow = MutableLiveData<ApiResponse<List<TvShowResultsItem>>>()
        ApiConfig.getApiService().getOnTheAirTvShow(API_KEY).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    tvShow.value = ApiResponse.Success(response.body()?.results as List<TvShowResultsItem>)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return tvShow
    }

    fun getPopularTvShow(): LiveData<ApiResponse<List<TvShowResultsItem>>> {
        val tvShow = MutableLiveData<ApiResponse<List<TvShowResultsItem>>>()
        ApiConfig.getApiService().getPopularTvShow(API_KEY).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    tvShow.value = ApiResponse.Success(response.body()?.results as List<TvShowResultsItem>)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return tvShow
    }

    fun getTopRatedTvShow(): LiveData<ApiResponse<List<TvShowResultsItem>>> {
        val tvShow = MutableLiveData<ApiResponse<List<TvShowResultsItem>>>()
        ApiConfig.getApiService().getTopRatedTvShow(API_KEY).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    tvShow.value = ApiResponse.Success(response.body()?.results as List<TvShowResultsItem>)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return tvShow
    }

    fun getDetailTvShow(tvId: Int): LiveData<ApiResponse<TvShowDetailResponse>> {
        val detailTvShow = MutableLiveData<ApiResponse<TvShowDetailResponse>>()
        ApiConfig.getApiService().getDetailTvShow(tvId, API_KEY)
            .enqueue(object : Callback<TvShowDetailResponse> {
                override fun onResponse(
                    call: Call<TvShowDetailResponse>,
                    response: Response<TvShowDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        detailTvShow.value = ApiResponse.Success(response.body() as TvShowDetailResponse)
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })

        return detailTvShow
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        private const val API_KEY = "96b03abc6c7393c9a3ec84d63863f5f4"
        private const val TAG = "RemoteDataSource"

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }
}
