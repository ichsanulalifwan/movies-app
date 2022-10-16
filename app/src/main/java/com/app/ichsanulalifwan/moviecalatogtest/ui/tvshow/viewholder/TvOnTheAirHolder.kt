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
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowOnTheAirEntity
import com.app.ichsanulalifwan.moviecalatogtest.databinding.ViewTvOnTheAirBinding
import com.app.ichsanulalifwan.moviecalatogtest.ui.detail.DetailTvShowActivity
import com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow.TvShowViewModel
import com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow.adapter.TvShowOnTheAirAdapter

class TvOnTheAirHolder(
    itemView: View,
    private val viewLifeCycleOwner: LifecycleOwner
) : TvShowViewHolder(itemView) {

    private val binding = ViewTvOnTheAirBinding.bind(itemView)
    private lateinit var context: Context
    private lateinit var viewModel: TvShowViewModel
    private lateinit var tvShowOnTheAirAdapter: TvShowOnTheAirAdapter

    override fun bind(context: Context, viewModel: TvShowViewModel) {
        this.context = context
        this.viewModel = viewModel

        tvShowOnTheAirAdapter = TvShowOnTheAirAdapter()
        setupRecyclerView()
        showLoading(true)
        initData()
        onItemSelected()
    }

    private fun initData() {

        viewModel.getOnTheAirTvShow().observe(viewLifeCycleOwner) { tv ->
            if (tv != null) {
                when (tv) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        tvShowOnTheAirAdapter.submitList(tv.data)
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
        with(binding.rvTvshowOnTheAir) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = tvShowOnTheAirAdapter
        }
    }

    private fun onItemSelected() {
        tvShowOnTheAirAdapter.setOnItemClickListener(object : TvShowOnTheAirAdapter.OnItemClickListener {
            override fun onMoviesClicked(tvShow: TvShowOnTheAirEntity) {
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