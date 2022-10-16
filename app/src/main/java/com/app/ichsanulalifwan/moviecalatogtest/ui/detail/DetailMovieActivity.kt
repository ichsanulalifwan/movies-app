package com.app.ichsanulalifwan.moviecalatogtest.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ichsanulalifwan.moviecalatogtest.R
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.data.model.GenreData
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieNowPlayingEntity
import com.app.ichsanulalifwan.moviecalatogtest.databinding.ActivityDetailMovieBinding
import com.app.ichsanulalifwan.moviecalatogtest.ui.movie.adapter.GenresAdapter
import com.app.ichsanulalifwan.moviecalatogtest.utils.Constant.IMAGE_PREFIX
import com.app.ichsanulalifwan.moviecalatogtest.utils.DataMapper
import com.app.ichsanulalifwan.moviecalatogtest.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var viewModel: DetailMovieViewModel
    private lateinit var genresAdapter: GenresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = ""
        }

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        genresAdapter = GenresAdapter()

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE_ID)
            viewModel.setSelectedMovie(movieId)

            viewModel.detailMovie.observe(this) { movie ->
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            if (movie.data != null) {
                                populateData(movie.data.mMovie)
                                getGenres(DataMapper.mapMovieGenreEntityToModel(movie.data.mGenre))
                                showLoading(false)
                                setWatchlistState(movie.data.mMovie.isWishlist)
                                onWatchlistClicked(movie.data.mMovie)
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

    private fun populateData(movie: MovieNowPlayingEntity) {
        binding.apply {
            tvOverview.text = movie.overview
            tvRelase.text = movie.releaseDate
            tvRuntime.text = movie.runtime.toString()
            tvTitleMovie.text = movie.title

            Glide.with(this@DetailMovieActivity)
                .load(IMAGE_PREFIX + movie.posterPath)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.movie_poster))
                .into(imgPosterMovie)
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

    private fun onWatchlistClicked(data: MovieNowPlayingEntity) {
        binding.btnWatchlistDetail.setOnClickListener {
            viewModel.setMovieFavorite()
            if (!data.isWishlist) Toast.makeText(
                this,
                data.title + " " + "Added to Watchlist",
                Toast.LENGTH_LONG
            ).show()
            else Toast.makeText(
                this,
                data.title + " " + "Deleted from Watchlist",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun setWatchlistState(state: Boolean) {
        binding.btnWatchlistDetail.run {
            if (state) {
                backgroundTintList =
                    ContextCompat.getColorStateList(applicationContext, R.color.white)
                icon =
                    ContextCompat.getDrawable(applicationContext, R.drawable.ic_baseline_check_24)
                iconTint = ContextCompat.getColorStateList(applicationContext, R.color.deep_black)
                setTextColor(
                    ContextCompat.getColorStateList(
                        applicationContext,
                        R.color.deep_black
                    )
                )
            } else {
                backgroundTintList =
                    ContextCompat.getColorStateList(applicationContext, R.color.light_black)
                icon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_baseline_add_24)
                iconTint = ContextCompat.getColorStateList(applicationContext, R.color.white)
                setTextColor(ContextCompat.getColorStateList(applicationContext, R.color.white))
            }
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
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
    }
}