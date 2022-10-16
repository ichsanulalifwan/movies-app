package com.app.ichsanulalifwan.moviecalatogtest.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.app.ichsanulalifwan.moviecalatogtest.data.AppRepository
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieNowPlayingEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MoviePopularEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieTopRatedEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieUpcomingEntity
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: AppRepository

    @Mock
    private lateinit var movieNowPlayingObserver: Observer<Resource<PagedList<MovieNowPlayingEntity>>>

    @Mock
    private lateinit var moviePopularObserver: Observer<Resource<PagedList<MoviePopularEntity>>>

    @Mock
    private lateinit var movieTopRatedObserver: Observer<Resource<PagedList<MovieTopRatedEntity>>>

    @Mock
    private lateinit var movieUpcomingObserver: Observer<Resource<PagedList<MovieUpcomingEntity>>>

    @Mock
    private lateinit var movieNowPlayingPagedList: PagedList<MovieNowPlayingEntity>

    @Mock
    private lateinit var moviePopularPagedList: PagedList<MoviePopularEntity>

    @Mock
    private lateinit var movieTopRatedPagedList: PagedList<MovieTopRatedEntity>

    @Mock
    private lateinit var movieUpcomingPagedList: PagedList<MovieUpcomingEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repository)
    }

    @Test
    fun getNowPlayingMovie() {
        val dummyMovies = Resource.Success(movieNowPlayingPagedList)
        `when`(dummyMovies.data?.size).thenReturn(6)

        val movie = MutableLiveData<Resource<PagedList<MovieNowPlayingEntity>>>()
        movie.value = dummyMovies
        `when`(repository.getNowPlayingMovie()).thenReturn(movie)

        // Check getNowPlayingMovie method is called and result
        val movieEntities = viewModel.getNowPlayingMovie().value?.data
        verify(repository).getNowPlayingMovie()
        assertNotNull(movieEntities)
        assertEquals(6, movieEntities?.size)

        // Check the observer is called
        viewModel.getNowPlayingMovie().observeForever(movieNowPlayingObserver)
        verify(movieNowPlayingObserver).onChanged(dummyMovies)
    }

    @Test
    fun getPopularMovie() {
        val dummyMovies = Resource.Success(moviePopularPagedList)
        `when`(dummyMovies.data?.size).thenReturn(10)

        val movie = MutableLiveData<Resource<PagedList<MoviePopularEntity>>>()
        movie.value = dummyMovies
        `when`(repository.getPopularMovie()).thenReturn(movie)

        // Check getPopularMovie method is called and result
        val movieEntities = viewModel.getPopularMovie().value?.data
        verify(repository).getPopularMovie()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        // Check the observer is called
        viewModel.getPopularMovie().observeForever(moviePopularObserver)
        verify(moviePopularObserver).onChanged(dummyMovies)
    }

    @Test
    fun getTopRatedMovie() {
        val dummyMovies = Resource.Success(movieTopRatedPagedList)
        `when`(dummyMovies.data?.size).thenReturn(8)

        val movie = MutableLiveData<Resource<PagedList<MovieTopRatedEntity>>>()
        movie.value = dummyMovies
        `when`(repository.getTopRatedMovie()).thenReturn(movie)

        // Check getTopRatedMovie method is called and result
        val movieEntities = viewModel.getTopRatedMovie().value?.data
        verify(repository).getTopRatedMovie()
        assertNotNull(movieEntities)
        assertEquals(8, movieEntities?.size)

        // Check the observer is called
        viewModel.getTopRatedMovie().observeForever(movieTopRatedObserver)
        verify(movieTopRatedObserver).onChanged(dummyMovies)
    }

    @Test
    fun getUpcomingMovie() {
        val dummyMovies = Resource.Success(movieUpcomingPagedList)
        `when`(dummyMovies.data?.size).thenReturn(11)

        val movie = MutableLiveData<Resource<PagedList<MovieUpcomingEntity>>>()
        movie.value = dummyMovies
        `when`(repository.getUpcomingMovie()).thenReturn(movie)

        // Check getUpcomingMovie method is called and result
        val movieEntities = viewModel.getUpcomingMovie().value?.data
        verify(repository).getUpcomingMovie()
        assertNotNull(movieEntities)
        assertEquals(11, movieEntities?.size)

        // Check the observer is called
        viewModel.getUpcomingMovie().observeForever(movieUpcomingObserver)
        verify(movieUpcomingObserver).onChanged(dummyMovies)
    }
}