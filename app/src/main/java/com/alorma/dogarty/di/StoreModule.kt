package com.alorma.dogarty.di

import com.alorma.dogarty.store.AppStore
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

object StoreModule {

    operator fun invoke() = module {
        factory { FirebaseFirestore.getInstance() }
        factory { AppStore(get()) }
    }
}