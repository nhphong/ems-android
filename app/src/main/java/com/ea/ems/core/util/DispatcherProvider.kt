package com.ea.ems.core.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {

    val io: CoroutineDispatcher

    val default: CoroutineDispatcher

    val main: CoroutineDispatcher

    val unconfined: CoroutineDispatcher
}

class DispatcherProviderImpl : DispatcherProvider {

    override val io = Dispatchers.IO

    override val default = Dispatchers.Default

    override val main = Dispatchers.Main

    override val unconfined = Dispatchers.Unconfined
}
