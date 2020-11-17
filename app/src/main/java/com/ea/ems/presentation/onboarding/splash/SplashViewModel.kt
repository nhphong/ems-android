package com.ea.ems.presentation.onboarding.splash

import com.ea.ems.core.util.SingleLiveEvent
import com.ea.ems.domain.usecase.CheckLoginStateUseCase
import com.ea.ems.presentation.base.BaseViewModel
import kotlinx.coroutines.delay

class SplashViewModel(
    private val checkLoginStateUseCase: CheckLoginStateUseCase
) : BaseViewModel() {

    val moveToWelcomeScreen = SingleLiveEvent<Unit>()
    val moveToHomeScreen = SingleLiveEvent<Unit>()

    init {
        launch(withLoading = false) {
            delay(SPLASH_DELAY)
            val isLoggedIn = checkLoginStateUseCase.execute()
            if (isLoggedIn) {
                moveToHomeScreen.value = Unit
            } else {
                moveToWelcomeScreen.value = Unit
            }
        }
    }
}

private const val SPLASH_DELAY = 3000L
