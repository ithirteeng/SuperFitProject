package com.ithirteeng.superfitproject.main.di

import com.ithirteeng.superfitproject.main.presentation.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    viewModel {
        MainScreenViewModel(application = get())
    }
}