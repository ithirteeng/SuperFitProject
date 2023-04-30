package com.ithirteeng.superfitproject

import android.app.Application
import com.ithirteeng.superfitproject.common.network.di.networkModule
import com.ithirteeng.superfitproject.common.token.di.tokenModule
import com.ithirteeng.superfitproject.signin.di.signInModule
import com.ithirteeng.superfitproject.splash.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@App)

            modules(
                tokenModule,
                networkModule,
                splashModule,
                signInModule
            )
        }
    }
}