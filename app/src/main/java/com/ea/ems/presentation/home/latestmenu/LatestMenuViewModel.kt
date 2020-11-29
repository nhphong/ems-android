package com.ea.ems.presentation.home.latestmenu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ea.ems.domain.model.MenuInfo
import com.ea.ems.domain.usecase.GetLatestMenuUseCase
import com.ea.ems.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class LatestMenuViewModel(
    getLatestMenuUseCase: GetLatestMenuUseCase
) : BaseViewModel() {

    val latestMenu = MutableLiveData<MenuInfo>()

    init {
        viewModelScope.launch {
            getLatestMenuUseCase
                .execute()
                .collectAndCatch {
                    latestMenu.value = it
                }
        }
    }
}
