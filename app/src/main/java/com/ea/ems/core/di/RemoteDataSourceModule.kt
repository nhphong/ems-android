package com.ea.ems.core.di

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { Firebase.firestore }
}
