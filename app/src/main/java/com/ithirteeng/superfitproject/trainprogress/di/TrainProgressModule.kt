package com.ithirteeng.superfitproject.trainprogress.di

import com.ithirteeng.superfitproject.trainprogress.presentation.TrainProgressViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val trainProgressModule = module {
    viewModel {
        TrainProgressViewModel(
            application = get(),
            getTrainingsUseCase = get()
        )
    }
}