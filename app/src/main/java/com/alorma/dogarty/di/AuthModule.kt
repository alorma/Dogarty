package com.alorma.dogarty.di

import com.alorma.dogarty.auth.AppAuth
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

object AuthModule {
    operator fun invoke() = module {
        factory { FirebaseAuth.getInstance() }
        factory { AppAuth(get()) }
    }
}