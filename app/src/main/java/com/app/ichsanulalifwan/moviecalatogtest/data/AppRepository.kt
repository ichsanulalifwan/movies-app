package com.app.ichsanulalifwan.moviecalatogtest.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.LocalDataSource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.*
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvDetailWithGenre
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvGenreEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.remote.RemoteDataSource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.remote.network.ApiResponse
import com.app.ichsanulalifwan.moviecalatogtest.data.source.remote.response.MovieDetailResponse
import com.app.ichsanulalifwan.moviecalatogtest.data.source.remote.response.MovieResultsItem
import com.app.ichsanulalifwan.moviecalatogtest.data.source.remote.response.TvShowDetailResponse
import com.app.ichsanulalifwan.moviecalatogtest.data.source.remote.response.TvShowResultsItem
import com.app.ichsanulalifwan.moviecalatogtest.utils.AppExecutors

class AppRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : AppDataSource {

    // Movies
    override fun getNowPlayingMovie(): LiveData<Resource<PagedList<MovieNowPlayingEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieNowPlayingEntity>, List<MovieResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieNowPlayingEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()
                return LivePagedListBuilder(localDataSource.getNowPlayingMovie(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieNowPlayingEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResultsItem>>> =
                remoteDataSource.getNowPlayingMovie()

            override fun saveCallResult(data: List<MovieResultsItem>) {
                val movieList = ArrayList<MovieNowPlayingEntity>()
                for (response in data) {
                    val movie = MovieNowPlayingEntity(
                        response.id,
                        response.title,
                        response.posterPath
                    )
                    movieList.add(movie)
                }
                localDataSource.insertNowPlayingMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getPopularMovie(): LiveData<Resource<PagedList<MoviePopularEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MoviePopularEntity>, List<MovieResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MoviePopularEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopularMovie(), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviePopularEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResultsItem>>> =
                remoteDataSource.getPopularMovie()

            override fun saveCallResult(data: List<MovieResultsItem>) {
                val movieList = ArrayList<MoviePopularEntity>()
                for (response in data) {
                    val movie = MoviePopularEntity(
                        response.id,
                        response.title,
                        response.posterPath
                    )
                    movieList.add(movie)
                }
                localDataSource.insertPopularMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getTopRatedMovie(): LiveData<Resource<PagedList<MovieTopRatedEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieTopRatedEntity>, List<MovieResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieTopRatedEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()
                return LivePagedListBuilder(localDataSource.getTopRatedMovie(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieTopRatedEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResultsItem>>> =
                remoteDataSource.getTopRatedMovie()

            override fun saveCallResult(data: List<MovieResultsItem>) {
                val movieList = ArrayList<MovieTopRatedEntity>()
                for (response in data) {
                    val movie = MovieTopRatedEntity(
                        response.id,
                        response.title,
                        response.posterPath
                    )
                    movieList.add(movie)
                }
                localDataSource.insertTopRatedMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getUpcomingMovie(): LiveData<Resource<PagedList<MovieUpcomingEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieUpcomingEntity>, List<MovieResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieUpcomingEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()
                return LivePagedListBuilder(localDataSource.getUpcomingMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieUpcomingEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResultsItem>>> =
                remoteDataSource.getUpcomingMovie()

            override fun saveCallResult(data: List<MovieResultsItem>) {
                val movieList = ArrayList<MovieUpcomingEntity>()
                for (response in data) {
                    val movie = MovieUpcomingEntity(
                        response.id,
                        response.title,
                        response.posterPath
                    )
                    movieList.add(movie)
                }
                localDataSource.insertUpcomingMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MovieDetailWithGenre>> {
        return object :
            NetworkBoundResource<MovieDetailWithGenre, MovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieDetailWithGenre> =
                localDataSource.getDetailMovieById(movieId)

            override fun shouldFetch(data: MovieDetailWithGenre?): Boolean =
                data?.mGenre == null || data.mGenre.isEmpty()

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getDetailMovie(movieId)

            override fun saveCallResult(data: MovieDetailResponse) {
                val movieData = ArrayList<MovieNowPlayingEntity>()
                movieData.add(
                    MovieNowPlayingEntity(
                        movieId,
                        data.title,
                        data.posterPath,
                        data.releaseDate,
                        data.overview,
                        data.runtime
                    )
                )
                localDataSource.insertNowPlayingMovie(movieData)

                val genreList = ArrayList<MovieGenreEntity>()
                for (response in data.genres) {
                    val genre = MovieGenreEntity(
                        movieId,
                        response.id,
                        response.name
                    )
                    genreList.add(genre)
                }
                localDataSource.insertMovieGenre(genreList)
            }
        }.asLiveData()
    }

    override fun getWishlistMovie(): LiveData<PagedList<MovieNowPlayingEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(6)
            .build()
        return LivePagedListBuilder(localDataSource.getWishlistMovie(), config).build()
    }

    override fun setWishlistMovie(movie: MovieNowPlayingEntity, state: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.setWishlistMovie(movie, state)
        }


    // TV Shows
    override fun getAiringTodayTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopularTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResultsItem>>> =
                remoteDataSource.getAiringTodayTvShow()

            override fun saveCallResult(data: List<TvShowResultsItem>) {
                val tvList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.name,
                        response.posterPath
                    )
                    tvList.add(tvShow)
                }
                localDataSource.insertTvShow(tvList)
            }
        }.asLiveData()
    }

    override fun getOnTheAirTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopularTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResultsItem>>> =
                remoteDataSource.getOnTheAirTvShow()

            override fun saveCallResult(data: List<TvShowResultsItem>) {
                val tvList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.name,
                        response.posterPath
                    )
                    tvList.add(tvShow)
                }
                localDataSource.insertTvShow(tvList)
            }
        }.asLiveData()
    }

    override fun getPopularTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopularTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResultsItem>>> =
                remoteDataSource.getPopularTvShow()

            override fun saveCallResult(data: List<TvShowResultsItem>) {
                val tvList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.name,
                        response.posterPath
                    )
                    tvList.add(tvShow)
                }
                localDataSource.insertTvShow(tvList)
            }
        }.asLiveData()
    }

    override fun getTopRatedTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopularTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResultsItem>>> =
                remoteDataSource.getTopRatedTvShow()

            override fun saveCallResult(data: List<TvShowResultsItem>) {
                val tvList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.name,
                        response.posterPath
                    )
                    tvList.add(tvShow)
                }
                localDataSource.insertTvShow(tvList)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(tvId: Int): LiveData<Resource<TvDetailWithGenre>> {
        return object :
            NetworkBoundResource<TvDetailWithGenre, TvShowDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvDetailWithGenre> =
                localDataSource.getDetailTvById(tvId)

            override fun shouldFetch(data: TvDetailWithGenre?): Boolean =
                data?.mGenre == null || data.mGenre.isEmpty()

            override fun createCall(): LiveData<ApiResponse<TvShowDetailResponse>> =
                remoteDataSource.getDetailTvShow(tvId)

            override fun saveCallResult(data: TvShowDetailResponse) {
                val tvData = ArrayList<TvShowEntity>()
                tvData.add(
                    TvShowEntity(
                        tvId,
                        data.name,
                        data.posterPath,
                        data.overview,
                        data.numberOfEpisodes,
                        data.numberOfSeasons
                    )
                )
                localDataSource.insertTvShow(tvData)

                val genreList = ArrayList<TvGenreEntity>()
                for (response in data.genres) {
                    val genre = TvGenreEntity(
                        tvId,
                        response.id,
                        response.name
                    )
                    genreList.add(genre)
                }
                localDataSource.insertTvShowGenre(genreList)
            }
        }.asLiveData()
    }

    override fun getWishlistTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(6)
            .build()
        return LivePagedListBuilder(localDataSource.getWishlistTvShow(), config).build()
    }

    override fun setWishlistTvShow(tv: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.setWishlistTvShow(tv, state)
        }

    companion object {
        @Volatile
        private var instance: AppRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(
                    remoteDataSource,
                    localDataSource,
                    appExecutors
                ).apply { instance = this }
            }
    }
}