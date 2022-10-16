package com.app.ichsanulalifwan.moviecalatogtest.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.ichsanulalifwan.moviecalatogtest.data.model.GenreData
import com.app.ichsanulalifwan.moviecalatogtest.databinding.ItemGenreBinding

class GenresAdapter : RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {

    private var listGenres = ArrayList<GenreData>()

    inner class GenresViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: GenreData) {
            with(binding) {
                tvGenres.text = genre.name
            }
        }
    }

    fun setData(genres: List<GenreData>?) {
        if (genres == null) return
        this.listGenres.clear()
        this.listGenres.addAll(genres)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val itemGenreBinding = ItemGenreBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return GenresViewHolder(itemGenreBinding)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.bind(listGenres[position])
    }

    override fun getItemCount(): Int = listGenres.size
}