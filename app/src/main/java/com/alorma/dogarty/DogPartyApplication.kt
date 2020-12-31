package com.alorma.dogarty

import android.app.Application
import com.alorma.dogarty.di.AuthModule
import com.alorma.dogarty.di.StoreModule
import com.alorma.dogarty.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class DogPartyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DogPartyApplication)
            modules(
                listOf(
                    AuthModule(),
                    StoreModule(),
                    ViewModelModule()
                )
            )
        }

        Timber.plant(Timber.DebugTree())
    }
}