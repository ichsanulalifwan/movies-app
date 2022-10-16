package com.app.ichsanulalifwan.moviecalatogtest.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ichsanulalifwan.moviecalatogtest.R
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.data.model.GenreData
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowAiringEntity
import com.app.ichsanulalifwan.moviecalatogtest.databinding.ActivityDetailTvShowBinding
import com.app.ichsanulalifwan.moviecalatogtest.ui.movie.adapter.GenresAdapter
import com.app.ichsanulalifwan.moviecalatogtest.utils.Constant.IMAGE_PREFIX
import com.app.ichsanulalifwan.moviecalatogtest.utils.DataMapper
import com.app.ichsanulalifwan.moviecalatogtest.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTvShowBinding
    private lateinit var viewModel: DetailTvShowViewModel
    private lateinit var genresAdapter: GenresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailTvShowViewModel::class.java]

        genresAdapter = GenresAdapter()

        val extras = intent.extras
        if (extras != null) {
            val tvId = extras.getInt(EXTRA_TV_ID)
            viewModel.setSelectedTvShow(tvId)

            viewModel.detailTv.observe(this) { tvShow ->
                if (tvShow != null) {
                    when (tvShow) {
                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            if (tvShow.data != null) {
                                populateData(tvShow.data.mTv)
                                getGenres(DataMapper.mapTvGenreEntityToModel(tvShow.data.mGenre))
//                                setFavouriteState(tvShow.data.mTv.isFavorite)
//                                onFavButtonClicked(tvShow.data.mTv)
                                showLoading(false)
                            }
                        }
                        is Resource.Error -> {
                            showLoading(false)
                            Toast.makeText(
                                applicationContext,
                                "Something Wrong",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun populateData(tvshow: TvShowAiringEntity) {
        binding.apply {
            tvTitleTvShow.text = tvshow.name
            tvSeason.text = tvshow.numberOfSeasons.toString()
            tvEpisode.text = tvshow.numberOfEpisodes.toString()
            tvOverview.text = tvshow.overview

            Glide.with(this@DetailTvShowActivity)
                .load(IMAGE_PREFIX + tvshow.posterPath)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.movie_poster))
                .into(imgPosterTv)
        }
    }

    private fun getGenres(genres: List<GenreData>) {
        genresAdapter.setData(genres)
        with(binding.rvGenres) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = genresAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun showLoading(state: Boolean) {
        if (state) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    companion object {
        const val EXTRA_TV_ID = "EXTRA_TV_ID"
    }
}