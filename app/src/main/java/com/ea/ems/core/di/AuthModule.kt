package com.ea.ems.core.di

import android.content.Context
import com.ea.ems.R
import com.ea.ems.core.util.FacebookLogInManager
import com.ea.ems.core.util.FacebookLogInManagerImpl
import com.ea.ems.core.util.GoogleSignInManager
import com.ea.ems.core.util.GoogleSignInManagerImpl
import com.ea.ems.data.remote.service.AuthService
import com.ea.ems.data.remote.service.FirebaseService
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val authModule = module {
    single<GoogleSignInManager> { GoogleSignInManagerImpl(buildGoogleSignInClient(get())) }
    single<FacebookLogInManager> { FacebookLogInManagerImpl(LoginManager.getInstance()) }
    single { Firebase.auth }
    factory<AuthService> { FirebaseService(get()) }
}

private fun buildGoogleSignInClient(context: Context) = GoogleSignIn.getClient(
    context,
    GoogleSignInOptions
        .Builder(DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .requestProfile()
        .build()
)
