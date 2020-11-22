package com.ea.ems.data.repo

import com.ea.ems.core.util.FacebookLogInManager
import com.ea.ems.core.util.GoogleSignInManager
import com.ea.ems.data.remote.model.toInfo
import com.ea.ems.data.remote.service.AuthService
import com.ea.ems.domain.model.UserInfo
import com.ea.ems.domain.repo.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val googleSignInManager: GoogleSignInManager,
    private val facebookLoginManager: FacebookLogInManager,
    private val authService: AuthService
) : AuthRepository {

    override val isLoggedIn: Boolean
        get() = firebaseAuth.currentUser != null

    override suspend fun signInWithGoogle(googleToken: String): UserInfo {
        return authService.signInWithGoogle(googleToken).toInfo()
    }

    override suspend fun loginWithFacebook(facebookToken: String): UserInfo {
        return authService.loginWithFacebook(facebookToken).toInfo()
    }

    override fun logOut() {
        firebaseAuth.signOut()
        facebookLoginManager.logOut()
        googleSignInManager.signOut()
    }
}
