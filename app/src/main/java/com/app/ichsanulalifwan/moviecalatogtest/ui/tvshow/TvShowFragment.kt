package com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ichsanulalifwan.moviecalatogtest.databinding.FragmentTvshowBinding
import com.app.ichsanulalifwan.moviecalatogtest.databinding.ViewTvAiringTodayBinding
import com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow.adapter.TvShowViewAdapter
import com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow.viewholder.AiringTodayHolder
import com.app.ichsanulalifwan.moviecalatogtest.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {

    private lateinit var viewModel: TvShowViewModel
    private var _binding: FragmentTvshowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvshowBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() = with(binding) {

        viewModel = ViewModelProvider(
            this@TvShowFragment,
            ViewModelFactory.getInstance(requireContext())
        )[TvShowViewModel::class.java]

        val tvShowViews = listOf(
            AiringTodayHolder(
                ViewTvAiringTodayBinding.inflate(
                    LayoutInflater.from(requireContext()),
                    binding.root,
                    false
                ).root,
                viewLifecycleOwner
            )
        )

        rvTvshowsContainer.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = TvShowViewAdapter(tvShowViews, viewModel)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}