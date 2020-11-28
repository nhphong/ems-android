package com.ea.ems.presentation.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ea.ems.R
import com.ea.ems.presentation.home.latestmenu.LatestMenuFragment
import com.ea.ems.presentation.home.myorder.MyOrderFragment

class HomePagerAdapter(
    private val parentFragment: Fragment
) : FragmentStateAdapter(parentFragment) {

    private val pages = listOf(MyOrderFragment(), LatestMenuFragment())

    override fun getItemCount(): Int = pages.count()

    override fun createFragment(position: Int): Fragment {
        return pages[position]
    }

    fun getTabTitle(index: Int): String {
        return when (index) {
            0 -> parentFragment.getString(R.string.tab_title_my_order)
            1 -> parentFragment.getString(R.string.tab_title_latest_menu)
            else -> ""
        }
    }
}
