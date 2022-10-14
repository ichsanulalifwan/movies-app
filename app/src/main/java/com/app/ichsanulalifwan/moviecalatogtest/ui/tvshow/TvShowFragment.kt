package com.app.ichsanulalifwan.moviecalatogtest.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.ichsanulalifwan.moviecalatogtest.databinding.FragmentTvshowBinding

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvshowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val tvShowViewModel =
            ViewModelProvider(this).get(TvShowViewModel::class.java)

        _binding = FragmentTvshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        tvShowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}