package com.ea.ems.domain.repo

import com.ea.ems.domain.model.MenuInfo
import kotlinx.coroutines.flow.Flow

interface EmsRepository {
    val latestEmsMenu: Flow<MenuInfo>
}
