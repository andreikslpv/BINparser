package com.andreikslpv.binparser

import android.app.Application
import com.andreikslpv.binparser.di.appModule
import com.andreikslpv.binparser.di.dataModule
import com.andreikslpv.binparser.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}