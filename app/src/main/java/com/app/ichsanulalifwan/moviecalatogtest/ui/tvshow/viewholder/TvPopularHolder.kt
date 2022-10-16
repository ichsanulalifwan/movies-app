package com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow.viewholder

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.ichsanulalifwan.moviecalatogtest.R
import com.app.ichsanulalifwan.moviecalatogtest.data.Resource
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowPopularEntity
import com.app.ichsanulalifwan.moviecalatogtest.databinding.ViewTvPopularBinding
import com.app.ichsanulalifwan.moviecalatogtest.ui.detail.DetailTvShowActivity
import com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow.TvShowViewModel
import com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow.adapter.TvShowPopularAdapter

class TvPopularHolder(
    itemView: View,
    private val viewLifeCycleOwner: LifecycleOwner
) : TvShowViewHolder(itemView) {

    private val binding = ViewTvPopularBinding.bind(itemView)
    private lateinit var context: Context
    private lateinit var viewModel: TvShowViewModel
    private lateinit var tvShowPopularAdapter: TvShowPopularAdapter

    override fun bind(context: Context, viewModel: TvShowViewModel) {
        this.context = context
        this.viewModel = viewModel

        tvShowPopularAdapter = TvShowPopularAdapter()
        setupRecyclerView()
        showLoading(true)
        initData()
        onItemSelected()
    }

    private fun initData() {

        viewModel.getPopularTvShow().observe(viewLifeCycleOwner) { tv ->
            if (tv != null) {
                when (tv) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        tvShowPopularAdapter.submitList(tv.data)
                        showLoading(false)
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        binding.viewError.tvError.text = tv.message ?: context.getString(R.string.something_wrong)
                        Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvTvshowPopular) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = tvShowPopularAdapter
        }
    }

    private fun onItemSelected() {
        tvShowPopularAdapter.setOnItemClickListener(object : TvShowPopularAdapter.OnItemClickListener {
            override fun onMoviesClicked(tvShow: TvShowPopularEntity) {
                val intent = Intent(context, DetailTvShowActivity::class.java)
                intent.putExtra(DetailTvShowActivity.EXTRA_TV_ID, tvShow.tvId)
                context.startActivity(intent)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }
}