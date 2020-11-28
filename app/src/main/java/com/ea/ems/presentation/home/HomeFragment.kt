package com.ea.ems.presentation.home

import com.ea.ems.R
import com.ea.ems.presentation.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_home

    override val viewModel: HomeViewModel by inject()

    override fun initUI() {
        super.initUI()
        HomePagerAdapter(this).let {
            vpHomePager.adapter = it
            TabLayoutMediator(tlHome, vpHomePager) { tab, position ->
                tab.text = it.getTabTitle(position)
            }.attach()
        }
    }
}
