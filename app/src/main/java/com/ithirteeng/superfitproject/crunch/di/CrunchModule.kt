package com.ithirteeng.superfitproject.crunch.di

import com.ithirteeng.superfitproject.crunch.presentation.CrunchScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val crunchModule = module {
    viewModel {
        CrunchScreenViewModel(
            setCrunchesAmountUseCase = get(),
            getCrunchesAmountUseCase = get(),
            saveTrainingUseCase = get(),
            addExerciseUseCase = get()
        )
    }
}