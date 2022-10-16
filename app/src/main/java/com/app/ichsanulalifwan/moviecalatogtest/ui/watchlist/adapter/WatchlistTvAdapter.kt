package com.app.ichsanulalifwan.moviecalatogtest.ui.watchlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.ichsanulalifwan.moviecalatogtest.R
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowAiringEntity
import com.app.ichsanulalifwan.moviecalatogtest.databinding.ItemWatchlistBinding
import com.app.ichsanulalifwan.moviecalatogtest.utils.Constant
import com.bumptech.glide.Glide

class WatchlistTvAdapter :
    PagedListAdapter<TvShowAiringEntity, WatchlistTvAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemWatchlistBinding = ItemWatchlistBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return MovieViewHolder(itemWatchlistBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }

    inner class MovieViewHolder(private val binding: ItemWatchlistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movies: TvShowAiringEntity) {
            with(binding) {
                itemView.setOnClickListener {
                    onItemClickListener.onItemClicked(movies)
                }

                Glide.with(itemView.context)
                    .load(Constant.IMAGE_PREFIX + movies.posterPath)
                    .centerCrop()
                    .placeholder(R.drawable.movie_poster)
                    .into(imgPoster)

                tvTitleWatchlist.text = movies.name
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(tv: TvShowAiringEntity)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowAiringEntity>() {
            override fun areItemsTheSame(
                oldItem: TvShowAiringEntity,
                newItem: TvShowAiringEntity
            ): Boolean {
                return oldItem.tvId == newItem.tvId
            }

            override fun areContentsTheSame(
                oldItem: TvShowAiringEntity,
                newItem: TvShowAiringEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}