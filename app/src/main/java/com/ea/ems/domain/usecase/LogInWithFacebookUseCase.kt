package com.ea.ems.domain.usecase

import com.ea.ems.domain.model.UserInfo
import com.ea.ems.domain.repo.AuthRepository

interface LogInWithFacebookUseCase {
    suspend fun execute(facebookToken: String): UserInfo
}

class LogInWithFacebookUseCaseImpl(
    private val authRepository: AuthRepository
) : LogInWithFacebookUseCase {

    override suspend fun execute(facebookToken: String): UserInfo {
        return authRepository.loginWithFacebook(facebookToken)
    }
}
