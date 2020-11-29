package com.ea.ems.presentation.home.latestmenu

import com.ea.ems.R
import com.ea.ems.core.util.loadImageUrl
import com.ea.ems.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_latest_menu.*
import org.koin.android.ext.android.inject

class LatestMenuFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_latest_menu

    override val viewModel: LatestMenuViewModel by inject()

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.run {
            latestMenu.observe {
                ivLatestMenu.loadImageUrl(it.imagePath)
            }
        }
    }
}
