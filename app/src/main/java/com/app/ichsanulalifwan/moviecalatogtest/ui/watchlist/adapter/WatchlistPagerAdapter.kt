package com.app.ichsanulalifwan.moviecalatogtest.ui.watchlist.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.ichsanulalifwan.moviecalatogtest.ui.watchlist.WatchlistItemFragment

class WatchlistPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return WatchlistItemFragment.newInstance(position)
    }
}