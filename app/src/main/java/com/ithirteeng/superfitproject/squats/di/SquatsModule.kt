package com.ithirteeng.superfitproject.squats.di

import com.ithirteeng.superfitproject.squats.presentation.SquatsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val squatsModule = module {
    viewModel {
        SquatsScreenViewModel(
            getSquatsAmountUseCase = get(),
            setSquatsAmountUseCase = get(),
            saveTrainingUseCase = get(),
            addExerciseUseCase = get()
        )
    }
}