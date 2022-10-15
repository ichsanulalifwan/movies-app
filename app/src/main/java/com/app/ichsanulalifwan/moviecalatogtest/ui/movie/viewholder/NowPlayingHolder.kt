package com.app.ichsanulalifwan.moviecalatogtest.ui.movie.viewholder

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.databinding.ViewMovieNowPlayingBinding
import com.app.ichsanulalifwan.moviecalatogtest.ui.movie.MovieViewModel
import com.app.ichsanulalifwan.moviecalatogtest.ui.movie.adapter.MovieAdapter
import com.app.ichsanulalifwan.moviecalatogtest.ui.movie.adapter.MovieViewHolder

class NowPlayingHolder(
    itemView: View,
    private val viewLifeCycleOwner: LifecycleOwner
) : MovieViewHolder(itemView) {

    private val binding = ViewMovieNowPlayingBinding.bind(itemView)
    private lateinit var context: Context
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun bind(context: Context, viewModel: MovieViewModel) {
        this.context = context
        this.viewModel = viewModel

        movieAdapter = MovieAdapter()
        setupRecyclerView()
        showLoading(true)
        initData()
    }

    private fun initData() {

        viewModel.getNowPlayingMovie().observe(viewLifeCycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        movieAdapter.submitList(movie.data)
                        showLoading(false)
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvNowPlaying) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

//    private fun onMovieSelected() {
//        movieAdapter.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {
//            override fun onMoviesClicked(movies: MovieEntity) {
//                val intent = Intent(context, DetailMovieActivity::class.java)
//                intent.putExtra(EXTRA_MOVIE_ID, movies.movieId)
//                startActivity(intent)
//            }
//        })
//    }

    private fun showLoading(state: Boolean) {
        if (state) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }
}