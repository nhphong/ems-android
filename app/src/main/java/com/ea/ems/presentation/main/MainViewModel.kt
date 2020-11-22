package com.ea.ems.presentation.main

import androidx.lifecycle.MutableLiveData
import com.ea.ems.core.annotation.AllOpen
import com.ea.ems.core.util.SingleLiveEvent
import com.ea.ems.presentation.base.BaseViewModel

@AllOpen
class MainViewModel : BaseViewModel() {

    val showBottomBar = MutableLiveData<Boolean>()
    val moveToHomeScreen = SingleLiveEvent<Unit>()

    fun showBottomBar() {
        showBottomBar.value = true
    }

    fun hideBottomBar() {
        showBottomBar.value = false
    }

    fun moveToHomeScreen() {
        moveToHomeScreen.value = Unit
    }
}
