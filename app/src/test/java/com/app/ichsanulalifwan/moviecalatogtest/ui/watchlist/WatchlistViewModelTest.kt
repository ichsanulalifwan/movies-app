package com.app.ichsanulalifwan.moviecalatogtest.ui.watchlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.app.ichsanulalifwan.moviecalatogtest.data.AppRepository
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieNowPlayingEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowAiringEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WatchlistViewModelTest {

    private lateinit var viewModel: WatchlistViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: AppRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieNowPlayingEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<PagedList<TvShowAiringEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieNowPlayingEntity>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowAiringEntity>

    @Before
    fun setUp() {
        viewModel = WatchlistViewModel(repository)
    }

    @Test
    fun getWatchlistMovie() {
        val dummyMovie = moviePagedList
        `when`(dummyMovie.size).thenReturn(20)

        val movies = MutableLiveData<PagedList<MovieNowPlayingEntity>>()
        movies.value = dummyMovie
        `when`(repository.getWatchlistMovie()).thenReturn(movies)

        // Check getWatchlistMovie method is called and result
        val tvEntities = viewModel.getWatchlistMovie().value
        verify(repository).getWatchlistMovie()
        assertNotNull(tvEntities)
        assertEquals(20, tvEntities?.size)

        // Check the observer is called
        viewModel.getWatchlistMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getWatchlistTvShow() {
        val dummyTv = tvShowPagedList
        `when`(dummyTv.size).thenReturn(10)

        val tvShow = MutableLiveData<PagedList<TvShowAiringEntity>>()
        tvShow.value = dummyTv
        `when`(repository.getWatchlistTvShow()).thenReturn(tvShow)

        // Check getWatchlistTvShow method is called and result
        val tvEntities = viewModel.getWatchlistTvShow().value
        verify(repository).getWatchlistTvShow()
        assertNotNull(tvEntities)
        assertEquals(10, tvEntities?.size)

        // Check the observer is called
        viewModel.getWatchlistTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTv)
    }
}