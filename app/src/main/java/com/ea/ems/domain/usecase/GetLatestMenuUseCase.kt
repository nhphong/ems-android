package com.ea.ems.domain.usecase

import com.ea.ems.domain.model.MenuInfo
import com.ea.ems.domain.repo.EmsRepository
import kotlinx.coroutines.flow.Flow

interface GetLatestMenuUseCase {
    fun execute(): Flow<MenuInfo>
}

class GetLatestMenuUseCaseImpl(
    private val emsRepository: EmsRepository
) : GetLatestMenuUseCase {

    override fun execute(): Flow<MenuInfo> {
        return emsRepository.latestEmsMenu
    }
}
