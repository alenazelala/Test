package com.example.test.ui.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@MyApplication)
            modules(listOf(networkModule, repositoryModule, useCasesModule, viewModelsModule))
        }
    }
}