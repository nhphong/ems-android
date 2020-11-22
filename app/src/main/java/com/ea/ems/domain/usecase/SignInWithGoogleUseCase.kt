package com.ea.ems.domain.usecase

import com.ea.ems.domain.model.UserInfo
import com.ea.ems.domain.repo.AuthRepository

interface SignInWithGoogleUseCase {
    suspend fun execute(googleToken: String): UserInfo
}

class SignInWithGoogleUseCaseImpl(
    private val authRepository: AuthRepository
) : SignInWithGoogleUseCase {

    override suspend fun execute(accessToken: String): UserInfo {
        return authRepository.signInWithGoogle(accessToken)
    }
}
