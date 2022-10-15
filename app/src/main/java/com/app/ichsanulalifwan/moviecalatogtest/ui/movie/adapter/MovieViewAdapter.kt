package com.app.ichsanulalifwan.moviecalatogtest.ui.movie.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.ichsanulalifwan.moviecalatogtest.ui.movie.MovieViewModel

class MovieViewAdapter(
    private val viewHolder: List<MovieViewHolder>,
    private val viewModel: MovieViewModel
) : RecyclerView.Adapter<MovieViewHolder>() {
    private lateinit var context: Context

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        context = parent.context
        return viewHolder[viewType]
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        viewHolder[position].bind(context, viewModel)
    }

    override fun getItemCount(): Int = viewHolder.size

}