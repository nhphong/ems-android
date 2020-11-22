package com.ea.ems.presentation.onboarding.login

import com.ea.ems.R
import com.ea.ems.presentation.base.BaseFragment
import com.ea.ems.presentation.base.NoBottomBar
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment(), NoBottomBar {

    override val layoutId = R.layout.fragment_login

    override val viewModel: LoginViewModel by inject()
}
