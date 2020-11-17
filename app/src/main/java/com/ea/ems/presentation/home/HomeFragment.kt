package com.ea.ems.presentation.home

import com.ea.ems.R
import com.ea.ems.presentation.base.BaseFragment
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_home

    override val viewModel: HomeViewModel by inject()
}
