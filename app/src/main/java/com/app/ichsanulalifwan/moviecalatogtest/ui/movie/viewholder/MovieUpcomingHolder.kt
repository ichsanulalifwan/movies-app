package com.app.ichsanulalifwan.moviecalatogtest.ui.movie.viewholder

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.ichsanulalifwan.moviecalatogtest.R
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieUpcomingEntity
import com.app.ichsanulalifwan.moviecalatogtest.databinding.ViewMovieUpcomingBinding
import com.app.ichsanulalifwan.moviecalatogtest.ui.detail.DetailMovieActivity
import com.app.ichsanulalifwan.moviecalatogtest.ui.movie.MovieViewModel
import com.app.ichsanulalifwan.moviecalatogtest.ui.movie.adapter.MovieUpcomingAdapter

class MovieUpcomingHolder(
    itemView: View,
    private val viewLifeCycleOwner: LifecycleOwner
) : MovieViewHolder(itemView) {

    private val binding = ViewMovieUpcomingBinding.bind(itemView)
    private lateinit var context: Context
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieUpcomingAdapter: MovieUpcomingAdapter

    override fun bind(context: Context, viewModel: MovieViewModel) {
        this.context = context
        this.viewModel = viewModel

        movieUpcomingAdapter = MovieUpcomingAdapter()
        setupRecyclerView()
        showLoading(true)
        initData()
        onMovieSelected()
    }

    private fun initData() {

        viewModel.getUpcomingMovie().observe(viewLifeCycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        movieUpcomingAdapter.submitList(movie.data)
                        showLoading(false)
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        binding.viewError.tvError.text =
                            movie.message ?: context.getString(R.string.something_wrong)
                        Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvMovieUpcoming) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = movieUpcomingAdapter
        }
    }

    private fun onMovieSelected() {

        movieUpcomingAdapter.setOnItemClickListener(object :
            MovieUpcomingAdapter.OnItemClickListener {
            override fun onMoviesClicked(movies: MovieUpcomingEntity) {
                val intent = Intent(context, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE_ID, movies.movieId)
                context.startActivity(intent)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        binding.run {
            if (state) {
                tvMovieUpcoming.visibility = View.GONE
                containerShimmerMovieUpcoming.shimmerSmallList.visibility = View.VISIBLE
            } else {
                tvMovieUpcoming.visibility = View.VISIBLE
                containerShimmerMovieUpcoming.shimmerSmallList.visibility = View.GONE
            }
        }
    }
}