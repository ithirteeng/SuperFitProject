package com.ithirteeng.superfitproject.splash.di

import com.ithirteeng.superfitproject.splash.presentation.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel {
        SplashScreenViewModel()
    }
}