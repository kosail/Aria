package com.korealm.aria.di

import com.korealm.aria.utils.AndroidUrlOpener
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single {
        AndroidUrlOpener(androidContext())
    }
}