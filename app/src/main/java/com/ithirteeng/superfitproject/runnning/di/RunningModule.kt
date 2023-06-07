package com.ithirteeng.superfitproject.runnning.di

import com.ithirteeng.superfitproject.runnning.presentation.RunningScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val runningModule = module {
    viewModel {
        RunningScreenViewModel()
    }
}