package com.ea.ems.data.remote.service

import com.ea.ems.data.remote.model.UserModel

interface AuthService {

    suspend fun signInWithGoogle(googleToken: String): UserModel

    suspend fun loginWithFacebook(facebookToken: String): UserModel
}
