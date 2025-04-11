package com.deekho.app.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.deekho.app.ui.fragments.DetailsFragment
import com.deekho.app.ui.fragments.PlaceholderFragment

class InfoPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val headings: List<String>
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = headings.size

    override fun createFragment(position: Int): Fragment {
        return when (headings[position]) {
            "Details" -> DetailsFragment()
            else -> PlaceholderFragment.newInstance(headings[position])
        }
    }
}
