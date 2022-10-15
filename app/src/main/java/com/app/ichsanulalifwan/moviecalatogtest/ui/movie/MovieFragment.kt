package com.app.ichsanulalifwan.moviecalatogtest.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ichsanulalifwan.moviecalatogtest.databinding.FragmentMovieBinding
import com.app.ichsanulalifwan.moviecalatogtest.databinding.ViewMovieNowPlayingBinding
import com.app.ichsanulalifwan.moviecalatogtest.databinding.ViewMoviePopularBinding
import com.app.ichsanulalifwan.moviecalatogtest.ui.movie.adapter.MovieViewAdapter
import com.app.ichsanulalifwan.moviecalatogtest.ui.movie.viewholder.MovieNowPlayingHolder
import com.app.ichsanulalifwan.moviecalatogtest.ui.movie.viewholder.MoviePopularHolder
import com.app.ichsanulalifwan.moviecalatogtest.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() = with(binding) {

        viewModel = ViewModelProvider(
            this@MovieFragment,
            ViewModelFactory.getInstance(requireContext())
        )[MovieViewModel::class.java]

        val movieViews = listOf(
            MovieNowPlayingHolder(
                ViewMovieNowPlayingBinding.inflate(
                    LayoutInflater.from(requireContext()),
                    binding.root,
                    false
                ).root,
                viewLifecycleOwner
            ),
            MoviePopularHolder(
                ViewMoviePopularBinding.inflate(
                    LayoutInflater.from(requireContext()),
                    binding.root,
                    false
                ).root,
                viewLifecycleOwner
            ),
        )

        rvMoviesContainer.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = MovieViewAdapter(movieViews, viewModel)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}