package com.ea.ems.data.remote.service

import com.ea.ems.core.util.CODE_INTERNAL_ERROR
import com.ea.ems.core.util.MSG_INTERNAL_ERROR
import com.ea.ems.data.remote.model.UserModel
import com.ea.ems.domain.model.BaseErrorInfo
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

class FirebaseService(
    private val auth: FirebaseAuth
) : AuthService {

    override suspend fun signInWithGoogle(googleToken: String): UserModel {
        val credential = GoogleAuthProvider.getCredential(googleToken, null)
        return auth.signInWithCredential(credential).await().toUserModel()
    }

    override suspend fun loginWithFacebook(facebookToken: String): UserModel {
        val credential = FacebookAuthProvider.getCredential(facebookToken)
        return auth.signInWithCredential(credential).await().toUserModel()
    }

    private suspend fun AuthResult.toUserModel(): UserModel {
        return user?.let {
            UserModel(
                email = it.email.orEmpty(),
                token = it.getIdToken(true).await().token.orEmpty()
            )
        } ?: throw BaseErrorInfo(CODE_INTERNAL_ERROR, MSG_INTERNAL_ERROR)
    }
}
