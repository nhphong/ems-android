package com.ea.ems.domain.usecase

import com.ea.ems.domain.repo.AuthRepository

interface CheckLoginStateUseCase {
    fun execute(): Boolean
}

class CheckLoginStateUseCaseImpl(
    private val authRepository: AuthRepository
) : CheckLoginStateUseCase {

    override fun execute(): Boolean {
        return authRepository.isLoggedIn
    }
}
