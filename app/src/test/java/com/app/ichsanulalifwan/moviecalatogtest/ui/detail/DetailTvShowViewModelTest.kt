package com.app.ichsanulalifwan.moviecalatogtest.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.app.ichsanulalifwan.moviecalatogtest.data.AppRepository
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvDetailWithGenre
import com.app.ichsanulalifwan.moviecalatogtest.utils.DataDummy
import com.app.ichsanulalifwan.moviecalatogtest.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {

    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTv = DataDummy.generateLocalDummyDetailTv()[0]
    private val tvId = dummyTv.tvId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appRepository: AppRepository

    @Mock
    private lateinit var tvObserver: Observer<Resource<TvDetailWithGenre>>

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(appRepository)
        viewModel.setSelectedTvShow(tvId)
    }

    @Test
    fun getDetailTv() {

        val dummyDetailTv = Resource.Success(DataDummy.generateDummyDetailTv(dummyTv, true))
        val detailTv = MutableLiveData<Resource<TvDetailWithGenre>>()
        detailTv.value = dummyDetailTv

        `when`(appRepository.getDetailTvShow(tvId)).thenReturn(detailTv)

        val tvEntity = LiveDataTestUtil.getValue(viewModel.detailTv)
        verify(appRepository).getDetailTvShow(tvId)
        assertNotNull(tvEntity)
        assertEquals(dummyTv.tvId, tvEntity.data?.mTv?.tvId)
        assertEquals(dummyTv.name, tvEntity.data?.mTv?.name)
        assertEquals(dummyTv.overview, tvEntity.data?.mTv?.overview)
        assertEquals(dummyTv.numberOfEpisodes, tvEntity.data?.mTv?.numberOfEpisodes)
        assertEquals(dummyTv.posterPath, tvEntity.data?.mTv?.posterPath)
        assertEquals(dummyTv.isWishlist, tvEntity.data?.mTv?.isWishlist)
        assertEquals(dummyTv.numberOfSeasons, tvEntity.data?.mTv?.numberOfSeasons)

        viewModel.detailTv.observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyDetailTv)
    }
}