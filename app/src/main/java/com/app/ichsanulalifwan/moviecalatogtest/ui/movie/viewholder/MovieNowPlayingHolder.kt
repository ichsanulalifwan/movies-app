package com.app.ichsanulalifwan.moviecalatogtest.ui.movie.viewholder

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.app.ichsanulalifwan.moviecalatogtest.R
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieNowPlayingEntity
import com.app.ichsanulalifwan.moviecalatogtest.databinding.ViewMovieNowPlayingBinding
import com.app.ichsanulalifwan.moviecalatogtest.ui.detail.DetailMovieActivity
import com.app.ichsanulalifwan.moviecalatogtest.ui.detail.DetailMovieActivity.Companion.EXTRA_MOVIE_ID
import com.app.ichsanulalifwan.moviecalatogtest.ui.movie.MovieViewModel
import com.app.ichsanulalifwan.moviecalatogtest.ui.movie.adapter.MovieNowPlayingAdapter


class MovieNowPlayingHolder(
    itemView: View,
    private val viewLifeCycleOwner: LifecycleOwner
) : MovieViewHolder(itemView) {

    private val binding = ViewMovieNowPlayingBinding.bind(itemView)
    private lateinit var context: Context
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieNowPlayingAdapter: MovieNowPlayingAdapter

    override fun bind(context: Context, viewModel: MovieViewModel) {
        this.context = context
        this.viewModel = viewModel

        movieNowPlayingAdapter = MovieNowPlayingAdapter()
        setupRecyclerView()
        showLoading(true)
        initData()
        onMovieSelected()
    }

    private fun initData() {

        viewModel.getNowPlayingMovie().observe(viewLifeCycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        movieNowPlayingAdapter.submitList(movie.data)
                        showLoading(false)
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        binding.viewError.tvError.text = movie.message ?: context.getString(R.string.something_wrong)
                        Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvMovieNowPlaying) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = movieNowPlayingAdapter

            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
    }

    private fun onMovieSelected() {
        movieNowPlayingAdapter.setOnItemClickListener(object :
            MovieNowPlayingAdapter.OnItemClickListener {
            override fun onMoviesClicked(movies: MovieNowPlayingEntity) {
                val intent = Intent(context, DetailMovieActivity::class.java)
                intent.putExtra(EXTRA_MOVIE_ID, movies.movieId)
                context.startActivity(intent)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }
}