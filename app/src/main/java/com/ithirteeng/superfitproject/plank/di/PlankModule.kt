package com.ithirteeng.superfitproject.plank.di

import com.ithirteeng.superfitproject.plank.presentation.PlankScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val plankModule = module {
    viewModel {
        PlankScreenViewModel(
            getPlankAmountUseCase = get(),
            setPlankAmountUseCase = get(),
            saveTrainingUseCase = get(),
            addExerciseUseCase = get()
        )
    }
}