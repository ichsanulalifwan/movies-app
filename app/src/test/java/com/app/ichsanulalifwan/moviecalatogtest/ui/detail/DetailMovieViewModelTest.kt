package com.app.ichsanulalifwan.moviecalatogtest.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.app.ichsanulalifwan.moviecalatogtest.data.AppRepository
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieDetailWithGenre
import com.app.ichsanulalifwan.moviecalatogtest.utils.DataDummy
import com.app.ichsanulalifwan.moviecalatogtest.utils.LiveDataTestUtil
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateLocalDummyDetailMovies()[0]
    private val movieId = dummyMovie.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appRepository: AppRepository

    @Mock
    private lateinit var detailObserver: Observer<Resource<MovieDetailWithGenre>>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(appRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getDetailMovie() {

        val dummyDetailMovie =
            Resource.Success(DataDummy.generateDummyDetailMovie(dummyMovie, true))
        val detailMovie = MutableLiveData<Resource<MovieDetailWithGenre>>()
        detailMovie.value = dummyDetailMovie

        `when`(appRepository.getDetailMovie(movieId)).thenReturn(detailMovie)

        val movieDeailEntity = LiveDataTestUtil.getValue(viewModel.detailMovie)
        verify(appRepository).getDetailMovie(movieId)
        assertNotNull(movieDeailEntity)
        assertEquals(dummyMovie.movieId, movieDeailEntity.data?.mMovie?.movieId)
        assertEquals(dummyMovie.title, movieDeailEntity.data?.mMovie?.title)
        assertEquals(dummyMovie.overview, movieDeailEntity.data?.mMovie?.overview)
        assertEquals(dummyMovie.releaseDate, movieDeailEntity.data?.mMovie?.releaseDate)
        assertEquals(dummyMovie.runtime, movieDeailEntity.data?.mMovie?.runtime)
        assertEquals(dummyMovie.posterPath, movieDeailEntity.data?.mMovie?.posterPath)
        assertEquals(dummyMovie.isWishlist, movieDeailEntity.data?.mMovie?.isWishlist)

        viewModel.detailMovie.observeForever(detailObserver)
        verify(detailObserver).onChanged(dummyDetailMovie)
    }
}