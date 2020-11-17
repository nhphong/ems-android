package com.ea.ems.domain.usecase

import com.ea.ems.domain.repo.AuthRepository

interface LogOutUseCase {
    fun execute()
}

class LogOutUseCaseImpl(
    private val authRepository: AuthRepository
) : LogOutUseCase {

    override fun execute() {
        authRepository.logOut()
    }
}
