package com.ea.ems.core.di

import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { FirebaseDatabase.getInstance() }
}
