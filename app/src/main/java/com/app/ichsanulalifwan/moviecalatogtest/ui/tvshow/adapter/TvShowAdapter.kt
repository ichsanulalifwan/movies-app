package com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.ichsanulalifwan.moviecalatogtest.R
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowEntity
import com.app.ichsanulalifwan.moviecalatogtest.databinding.ItemListBinding
import com.app.ichsanulalifwan.moviecalatogtest.utils.Constant.IMAGE_PREFIX
import com.bumptech.glide.Glide

class TvShowAdapter : PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK)  {

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemTvShowBinding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return TvShowViewHolder(itemTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    inner class TvShowViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                itemView.setOnClickListener {
                    onItemClickListener.onMoviesClicked(tvShow)
                }

                Glide.with(itemView.context)
                    .load(IMAGE_PREFIX + tvShow.posterPath)
                    .centerCrop()
                    .placeholder(R.drawable.movie_poster)
                    .into(imgPoster)
            }
        }
    }

    interface OnItemClickListener {
        fun onMoviesClicked(tvShow: TvShowEntity)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvId == newItem.tvId
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}