package com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow.viewholder

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowTopRatedEntity
import com.app.ichsanulalifwan.moviecalatogtest.databinding.ViewTvTopRatedBinding
import com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow.TvShowViewModel
import com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow.adapter.TvShowTopRatedAdapter

class TvTopRatedHolder(
    itemView: View,
    private val viewLifeCycleOwner: LifecycleOwner
) : TvShowViewHolder(itemView) {

    private val binding = ViewTvTopRatedBinding.bind(itemView)
    private lateinit var context: Context
    private lateinit var viewModel: TvShowViewModel
    private lateinit var tvShowTopRatedAdapter: TvShowTopRatedAdapter

    override fun bind(context: Context, viewModel: TvShowViewModel) {
        this.context = context
        this.viewModel = viewModel

        tvShowTopRatedAdapter = TvShowTopRatedAdapter()
        setupRecyclerView()
        showLoading(true)
        initData()
        onMovieSelected()
    }

    private fun initData() {

        viewModel.getTopRatedTvShow().observe(viewLifeCycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        tvShowTopRatedAdapter.submitList(movie.data)
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
        with(binding.rvTvshowTopRated) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = tvShowTopRatedAdapter
        }
    }

    private fun onMovieSelected() {
        tvShowTopRatedAdapter.setOnItemClickListener(object : TvShowTopRatedAdapter.OnItemClickListener {
            override fun onMoviesClicked(tvShow: TvShowTopRatedEntity) {
//                val intent = Intent(context, DetailMovieActivity::class.java)
//                intent.putExtra(EXTRA_MOVIE_ID, movies.movieId)
//                startActivity(intent)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }
}