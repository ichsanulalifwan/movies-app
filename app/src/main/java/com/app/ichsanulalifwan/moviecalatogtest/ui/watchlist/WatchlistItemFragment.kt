package com.app.ichsanulalifwan.moviecalatogtest.ui.watchlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.movie.MovieNowPlayingEntity
import com.app.ichsanulalifwan.moviecalatogtest.data.source.local.entity.tvshow.TvShowAiringEntity
import com.app.ichsanulalifwan.moviecalatogtest.databinding.FragmentWatchlistItemBinding
import com.app.ichsanulalifwan.moviecalatogtest.ui.detail.DetailMovieActivity
import com.app.ichsanulalifwan.moviecalatogtest.ui.detail.DetailTvShowActivity
import com.app.ichsanulalifwan.moviecalatogtest.ui.watchlist.adapter.WatchlistMovieAdapter
import com.app.ichsanulalifwan.moviecalatogtest.ui.watchlist.adapter.WatchlistTvAdapter
import com.app.ichsanulalifwan.moviecalatogtest.viewmodel.ViewModelFactory

class WatchlistItemFragment : Fragment() {

    private lateinit var viewModel: WatchlistViewModel
    private lateinit var movieAdapter: WatchlistMovieAdapter
    private lateinit var tvAdapter: WatchlistTvAdapter
    private var _binding: FragmentWatchlistItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWatchlistItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[WatchlistViewModel::class.java]

            movieAdapter = WatchlistMovieAdapter()
            tvAdapter = WatchlistTvAdapter()

            setupRecyclerView()
            showLoading(true)

            when (index) {
                0 -> showWatchlistMovie()
                1 -> showWatchlistTv()
            }
        }
    }

    private fun showWatchlistMovie() {
        binding.rvWatchlistContainer.adapter = movieAdapter
        viewModel.getWatchlistMovie().observe(viewLifecycleOwner) { movie ->
            if (movie != null && movie.isNotEmpty()) {
                movieAdapter.submitList(movie)
                binding.viewEmpty.root.visibility = View.GONE
            } else binding.viewEmpty.root.visibility = View.VISIBLE
        }
        showLoading(false)

        movieAdapter.setOnItemClickListener(object : WatchlistMovieAdapter.OnItemClickListener {
            override fun onItemClicked(movies: MovieNowPlayingEntity) {
                val intent = Intent(context, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE_ID, movies.movieId)
                startActivity(intent)
            }
        })
    }

    private fun showWatchlistTv() {
        binding.rvWatchlistContainer.adapter = tvAdapter
        viewModel.getWatchlistTvShow().observe(viewLifecycleOwner) { tv ->
            if (tv != null && tv.isNotEmpty()) {
                tvAdapter.submitList(tv)
                binding.viewEmpty.root.visibility = View.GONE
            } else binding.viewEmpty.root.visibility = View.VISIBLE
        }
        showLoading(false)

        tvAdapter.setOnItemClickListener(object : WatchlistTvAdapter.OnItemClickListener {
            override fun onItemClicked(tv: TvShowAiringEntity) {
                val intent = Intent(context, DetailTvShowActivity::class.java)
                intent.putExtra(DetailTvShowActivity.EXTRA_TV_ID, tv.tvId)
                startActivity(intent)
            }
        })
    }

    private fun setupRecyclerView() {
        with(binding.rvWatchlistContainer) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(index: Int): WatchlistItemFragment {
            val fragment = WatchlistItemFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_SECTION_NUMBER, index)
            fragment.arguments = bundle
            return fragment
        }
    }
}