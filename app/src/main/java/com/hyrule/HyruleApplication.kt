package com.hyrule

import android.app.Application
import com.hyrule.di.injectModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HyruleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@HyruleApplication)
            injectModules()
        }
    }
}
