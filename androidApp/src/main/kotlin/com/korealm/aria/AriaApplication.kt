package com.korealm.aria

import android.app.Application
import com.korealm.aria.di.androidModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AriaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AriaApplication)
            modules(androidModule)
        }
    }
}
