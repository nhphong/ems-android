package com.ea.ems.core.di

import com.ea.ems.core.util.DispatcherProvider
import com.ea.ems.core.util.DispatcherProviderImpl
import com.ea.ems.core.util.NotificationUtil
import com.ea.ems.core.util.NotificationUtilImpl
import com.ea.ems.core.view.DialogUtil
import com.ea.ems.core.view.DialogUtilImpl
import com.ea.ems.core.view.Toaster
import com.ea.ems.core.view.ToasterImpl
import com.ea.ems.data.repo.AuthRepositoryImpl
import com.ea.ems.domain.repo.AuthRepository
import com.ea.ems.domain.usecase.*
import com.ea.ems.presentation.home.HomeViewModel
import com.ea.ems.presentation.info.InfoViewModel
import com.ea.ems.presentation.main.MainViewModel
import com.ea.ems.presentation.onboarding.login.LoginViewModel
import com.ea.ems.presentation.onboarding.signup.SignupViewModel
import com.ea.ems.presentation.onboarding.splash.SplashViewModel
import com.ea.ems.presentation.onboarding.welcome.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<SignInWithGoogleUseCase> { SignInWithGoogleUseCaseImpl(get()) }
    factory<LogInWithFacebookUseCase> { LogInWithFacebookUseCaseImpl(get()) }
    factory<CheckLoginStateUseCase> { CheckLoginStateUseCaseImpl(get()) }
    factory<LogOutUseCase> { LogOutUseCaseImpl(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { SplashViewModel(get()) }
    viewModel { WelcomeViewModel(get(), get()) }
    viewModel { LoginViewModel() }
    viewModel { SignupViewModel() }
    viewModel { HomeViewModel() }
    viewModel { InfoViewModel() }
}

val repositoryModule = module {
    factory<AuthRepository> { AuthRepositoryImpl(get(), get(), get(), get()) }
}

val utilModule = module {
    single<DispatcherProvider> { DispatcherProviderImpl() }
    single<Toaster> { ToasterImpl(get()) }
    single<DialogUtil> { DialogUtilImpl() }
    single<NotificationUtil> { NotificationUtilImpl(get()) }
}
