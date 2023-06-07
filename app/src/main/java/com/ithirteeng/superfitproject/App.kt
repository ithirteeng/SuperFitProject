package com.ithirteeng.superfitproject

import android.app.Application
import com.ithirteeng.superfitproject.common.exercises.di.exercisesModule
import com.ithirteeng.superfitproject.common.network.di.networkModule
import com.ithirteeng.superfitproject.common.photos.di.photosModule
import com.ithirteeng.superfitproject.common.token.di.tokenModule
import com.ithirteeng.superfitproject.common.validation.di.validationModule
import com.ithirteeng.superfitproject.crunch.di.crunchModule
import com.ithirteeng.superfitproject.exerciseslist.di.exercisesScreenModule
import com.ithirteeng.superfitproject.image.di.imageModule
import com.ithirteeng.superfitproject.imagelist.di.imageListModule
import com.ithirteeng.superfitproject.main.di.mainModule
import com.ithirteeng.superfitproject.mybody.di.myBodyModule
import com.ithirteeng.superfitproject.plank.di.plankModule
import com.ithirteeng.superfitproject.pushups.di.pushUpsModule
import com.ithirteeng.superfitproject.signin.di.signInModule
import com.ithirteeng.superfitproject.signup.di.signUpModule
import com.ithirteeng.superfitproject.splash.di.splashModule
import com.ithirteeng.superfitproject.squats.di.squatsModule
import com.ithirteeng.superfitproject.result.di.resultModule
import com.ithirteeng.superfitproject.trainprogress.di.trainProgressModule
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
                signInModule,
                signUpModule,
                validationModule,
                mainModule,
                exercisesModule,
                exercisesScreenModule,
                myBodyModule,
                crunchModule,
                plankModule,
                resultModule,
                squatsModule,
                pushUpsModule,
                photosModule,
                trainProgressModule,
                imageListModule,
                imageModule
            )
        }
    }
}