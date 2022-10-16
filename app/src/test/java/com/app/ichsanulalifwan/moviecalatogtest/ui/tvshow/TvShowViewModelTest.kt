package com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.app.ichsanulalifwan.moviecalatogtest.data.AppRepository
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowAiringEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowOnTheAirEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowPopularEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowTopRatedEntity
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
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: AppRepository

    @Mock
    private lateinit var tvAiringObserver: Observer<Resource<PagedList<TvShowAiringEntity>>>

    @Mock
    private lateinit var tvOnTheAirObserver: Observer<Resource<PagedList<TvShowOnTheAirEntity>>>

    @Mock
    private lateinit var tvPopularObserver: Observer<Resource<PagedList<TvShowPopularEntity>>>

    @Mock
    private lateinit var tvTopRatedObserver: Observer<Resource<PagedList<TvShowTopRatedEntity>>>

    @Mock
    private lateinit var tvAiringPagedList: PagedList<TvShowAiringEntity>

    @Mock
    private lateinit var tvOnTheAirPagedList: PagedList<TvShowOnTheAirEntity>

    @Mock
    private lateinit var tvPopularPagedList: PagedList<TvShowPopularEntity>

    @Mock
    private lateinit var tvTopRatedPagedList: PagedList<TvShowTopRatedEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(repository)
    }

    @Test
    fun getAiringTodayTvShow() {
        val dummyTv = Resource.Success(tvAiringPagedList)
        `when`(dummyTv.data?.size).thenReturn(12)

        val tvShow = MutableLiveData<Resource<PagedList<TvShowAiringEntity>>>()
        tvShow.value = dummyTv
        `when`(repository.getAiringTodayTvShow()).thenReturn(tvShow)

        // Check getAiringTodayTvShow method is called and result
        val tvEntities = viewModel.getAiringTodayTvShow().value?.data
        verify(repository).getAiringTodayTvShow()
        assertNotNull(tvEntities)
        assertEquals(12, tvEntities?.size)

        // Check the observer is called
        viewModel.getAiringTodayTvShow().observeForever(tvAiringObserver)
        verify(tvAiringObserver).onChanged(dummyTv)
    }

    @Test
    fun getOnTheAirTvShow() {
        val dummyTv = Resource.Success(tvOnTheAirPagedList)
        `when`(dummyTv.data?.size).thenReturn(8)

        val tvShow = MutableLiveData<Resource<PagedList<TvShowOnTheAirEntity>>>()
        tvShow.value = dummyTv
        `when`(repository.getOnTheAirTvShow()).thenReturn(tvShow)

        // Check getOnTheAirTvShow method is called and result
        val tvEntities = viewModel.getOnTheAirTvShow().value?.data
        verify(repository).getOnTheAirTvShow()
        assertNotNull(tvEntities)
        assertEquals(8, tvEntities?.size)

        // Check the observer is called
        viewModel.getOnTheAirTvShow().observeForever(tvOnTheAirObserver)
        verify(tvOnTheAirObserver).onChanged(dummyTv)
    }

    @Test
    fun getPopularTvShow() {
        val dummyTv = Resource.Success(tvPopularPagedList)
        `when`(dummyTv.data?.size).thenReturn(6)

        val tvShow = MutableLiveData<Resource<PagedList<TvShowPopularEntity>>>()
        tvShow.value = dummyTv
        `when`(repository.getPopularTvShow()).thenReturn(tvShow)

        // Check getPopularTvShow method is called and result
        val tvEntities = viewModel.getPopularTvShow().value?.data
        verify(repository).getPopularTvShow()
        assertNotNull(tvEntities)
        assertEquals(6, tvEntities?.size)

        // Check the observer is called
        viewModel.getPopularTvShow().observeForever(tvPopularObserver)
        verify(tvPopularObserver).onChanged(dummyTv)
    }

    @Test
    fun getTopRatedTvShow() {
        val dummyTv = Resource.Success(tvTopRatedPagedList)
        `when`(dummyTv.data?.size).thenReturn(4)

        val tvShow = MutableLiveData<Resource<PagedList<TvShowTopRatedEntity>>>()
        tvShow.value = dummyTv
        `when`(repository.getTopRatedTvShow()).thenReturn(tvShow)

        // Check getTopRatedTvShow method is called and result
        val tvEntities = viewModel.getTopRatedTvShow().value?.data
        verify(repository).getTopRatedTvShow()
        assertNotNull(tvEntities)
        assertEquals(4, tvEntities?.size)

        // Check the observer is called
        viewModel.getTopRatedTvShow().observeForever(tvTopRatedObserver)
        verify(tvTopRatedObserver).onChanged(dummyTv)
    }
}