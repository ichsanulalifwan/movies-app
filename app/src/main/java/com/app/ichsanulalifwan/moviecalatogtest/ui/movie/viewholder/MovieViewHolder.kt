package com.app.ichsanulalifwan.moviecalatogtest.ui.movie.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.ichsanulalifwan.moviecalatogtest.ui.movie.MovieViewModel

abstract class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(context: Context, viewModel: MovieViewModel)
}