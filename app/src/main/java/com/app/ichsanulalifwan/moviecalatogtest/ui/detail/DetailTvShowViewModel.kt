package com.app.ichsanulalifwan.moviecalatogtest.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.app.ichsanulalifwan.moviecalatogtest.data.AppRepository
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvDetailWithGenre

class DetailTvShowViewModel(private val repository: AppRepository) : ViewModel() {

    val tvId = MutableLiveData<Int>()

    var detailTv: LiveData<Resource<TvDetailWithGenre>> =
        Transformations.switchMap(tvId) { tvId ->
            repository.getDetailTvShow(tvId)
        }

    fun setSelectedTvShow(tvId: Int) {
        this.tvId.value = tvId
    }

    fun setWatchlistTv() {
        val tvResource = detailTv.value
        if (tvResource != null) {
            val tv = tvResource.data
            if (tv != null) {
                val tvEntity = tv.mTv
                val newState = !tvEntity.isWishlist
                repository.setWatchlistTvShow(tvEntity, newState)
            }
        }
    }
}