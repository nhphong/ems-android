package com.ea.ems.presentation.onboarding.splash

import androidx.navigation.fragment.findNavController
import com.ea.ems.R
import com.ea.ems.presentation.base.BaseFragment
import com.ea.ems.presentation.base.NoBottomBar
import org.koin.android.ext.android.inject

class SplashFragment : BaseFragment(), NoBottomBar {

    override val layoutId = R.layout.fragment_splash

    override val viewModel: SplashViewModel by inject()

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.run {
            moveToWelcomeScreen.observe {
                findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
            }
            moveToHomeScreen.observe {
                mainViewModel.moveToHomeScreen()
            }
        }
    }
}
