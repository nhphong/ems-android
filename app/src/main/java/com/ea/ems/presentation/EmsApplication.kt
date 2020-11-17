package com.ea.ems.presentation

import android.app.Application
import com.ea.ems.BuildConfig
import com.ea.ems.core.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

open class EmsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val moduleList = listOf(
            authModule,
            useCaseModule,
            viewModelModule,
            repositoryModule,
            utilModule
        )
        startKoin {
            androidContext(this@EmsApplication)
            modules(moduleList)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
