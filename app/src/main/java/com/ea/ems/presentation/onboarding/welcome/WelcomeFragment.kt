package com.ea.ems.presentation.onboarding.welcome

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ea.ems.R
import com.ea.ems.core.util.FacebookLogInManager
import com.ea.ems.core.util.GoogleSignInManager
import com.ea.ems.core.util.NotificationUtil
import com.ea.ems.presentation.base.BaseFragment
import com.ea.ems.presentation.base.NoBottomBar
import kotlinx.android.synthetic.main.fragment_welcome.*
import org.koin.android.ext.android.inject

class WelcomeFragment : BaseFragment(), NoBottomBar {

    private val facebookLogInManager: FacebookLogInManager by inject()

    private val googleSignInManager: GoogleSignInManager by inject()

    private val notificationUtil: NotificationUtil by inject()

    override val layoutId = R.layout.fragment_welcome

    override val viewModel: WelcomeViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewEvents()
        bindViewModel()
    }

    private fun bindViewEvents() {
        btWelcomeSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_SignupFragment)
        }
        tvWelcomeLogin.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_LoginFragment)
        }
        ivWelcomeFacebook.setOnClickListener {
            loginWithFacebook()
        }
        ivWelcomeGoogle.setOnClickListener {
            logInWithGoogle()
        }
        tvWelcomeSkip.setOnClickListener {
            mainViewModel.moveToHomeScreen()
            notificationUtil.displayNotification("TITLE", "MESSAGE", "DETAILS")
        }
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.run {
            moveToHomeScreen.observe {
                mainViewModel.moveToHomeScreen()
            }
        }
    }

    private fun loginWithFacebook() {
        facebookLogInManager.logIn(
            fragment = this,
            onSuccess = viewModel::logInWithFacebook,
            onError = toaster::display
        )
    }

    private fun logInWithGoogle() {
        googleSignInManager
            .signIn(
                fragment = this,
                onSuccess = viewModel::signInWithGoogle,
                onError = toaster::display
            )
    }
}
