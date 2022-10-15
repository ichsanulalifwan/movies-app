package com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow.TvShowViewModel

abstract class TvShowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(context: Context, viewModel: TvShowViewModel)
}