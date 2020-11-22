package com.ea.ems.presentation.onboarding.signup

import com.ea.ems.R
import com.ea.ems.presentation.base.BaseFragment
import com.ea.ems.presentation.base.NoBottomBar
import org.koin.android.ext.android.inject

class SignupFragment : BaseFragment(), NoBottomBar {

    override val layoutId = R.layout.fragment_signup

    override val viewModel: SignupViewModel by inject()
}
