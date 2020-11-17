package com.ea.ems.presentation.onboarding.welcome

import com.ea.ems.core.util.SingleLiveEvent
import com.ea.ems.domain.usecase.LogInWithFacebookUseCase
import com.ea.ems.domain.usecase.SignInWithGoogleUseCase
import com.ea.ems.presentation.base.BaseViewModel

class WelcomeViewModel(
    private val logInWithFacebookUseCase: LogInWithFacebookUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase
) : BaseViewModel() {

    val moveToHomeScreen = SingleLiveEvent<Unit>()

    fun logInWithFacebook(facebookToken: String) {
        launch {
            runIO {
                logInWithFacebookUseCase.execute(facebookToken)
            }
            moveToHomeScreen.value = Unit
        }
    }

    fun signInWithGoogle(googleToken: String) {
        launch {
            runIO {
                signInWithGoogleUseCase.execute(googleToken)
            }
            moveToHomeScreen.value = Unit
        }
    }
}
