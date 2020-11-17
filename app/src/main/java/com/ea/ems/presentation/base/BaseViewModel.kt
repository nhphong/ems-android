package com.ea.ems.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ea.ems.core.annotation.AllOpen
import com.ea.ems.core.util.DispatcherProvider
import com.ea.ems.core.util.SingleLiveEvent
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

@AllOpen
class BaseViewModel : ViewModel(), KoinComponent {

    val dispatcherProvider: DispatcherProvider by inject()
    val isLoading = SingleLiveEvent<Boolean>()
    val errorMessage = SingleLiveEvent<String>()

    final fun launch(withLoading: Boolean = true, block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                Timber.e(throwable)
                if (withLoading) {
                    hideLoading()
                }
                errorMessage.value = throwable.message
            }
        ) {
            if (withLoading) {
                showLoading()
            }
            block()
            if (withLoading) {
                hideLoading()
            }
        }
    }

    suspend fun <T> runIO(block: suspend CoroutineScope.() -> T): T {
        return withContext(dispatcherProvider.io, block)
    }

    protected fun showLoading() {
        isLoading.value = true
    }

    protected fun hideLoading() {
        isLoading.value = false
    }
}

object DummyViewModel : BaseViewModel()
