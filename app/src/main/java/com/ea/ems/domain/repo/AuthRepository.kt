package com.ea.ems.domain.repo

import com.ea.ems.domain.model.UserInfo

interface AuthRepository {

    val isLoggedIn: Boolean

    suspend fun signInWithGoogle(googleToken: String): UserInfo

    suspend fun loginWithFacebook(facebookToken: String): UserInfo

    fun logOut()
}
