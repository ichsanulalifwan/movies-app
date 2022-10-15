package com.app.ichsanulalifwan.moviecalatogtest.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.ichsanulalifwan.moviecalatogtest.R
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieUpcomingEntity
import com.app.ichsanulalifwan.moviecalatogtest.databinding.ItemListBinding
import com.app.ichsanulalifwan.moviecalatogtest.utils.Constant.IMAGE_PREFIX
import com.bumptech.glide.Glide

class MovieUpcomingAdapter : PagedListAdapter<MovieUpcomingEntity, MovieUpcomingAdapter.MovieViewHolder>(DIFF_CALLBACK)  {

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }

    inner class MovieViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movies: MovieUpcomingEntity) {
            with(binding) {
                itemView.setOnClickListener {
                    onItemClickListener.onMoviesClicked(movies)
                }

                Glide.with(itemView.context)
                    .load(IMAGE_PREFIX + movies.posterPath)
                    .centerCrop()
                    .placeholder(R.drawable.movie_poster)
                    .into(imgPoster)
            }
        }
    }

    interface OnItemClickListener {
        fun onMoviesClicked(movies: MovieUpcomingEntity)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieUpcomingEntity>() {
            override fun areItemsTheSame(oldItem: MovieUpcomingEntity, newItem: MovieUpcomingEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MovieUpcomingEntity, newItem: MovieUpcomingEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}