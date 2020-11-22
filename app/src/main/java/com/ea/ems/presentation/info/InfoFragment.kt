package com.ea.ems.presentation.info

import com.ea.ems.R
import com.ea.ems.presentation.base.BaseFragment
import org.koin.android.ext.android.inject

class InfoFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_info

    override val viewModel: InfoViewModel by inject()
}
