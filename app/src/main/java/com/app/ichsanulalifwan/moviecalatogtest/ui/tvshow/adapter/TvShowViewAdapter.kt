package com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow.TvShowViewModel
import com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow.viewholder.TvShowViewHolder

class TvShowViewAdapter(
    private val viewHolder: List<TvShowViewHolder>,
    private val viewModel: TvShowViewModel
) : RecyclerView.Adapter<TvShowViewHolder>() {
    private lateinit var context: Context

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        context = parent.context
        return viewHolder[viewType]
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        viewHolder[position].bind(context, viewModel)
    }

    override fun getItemCount(): Int = viewHolder.size

}