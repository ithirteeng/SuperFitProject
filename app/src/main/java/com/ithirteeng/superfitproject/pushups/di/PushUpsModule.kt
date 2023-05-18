package com.ithirteeng.superfitproject.pushups.di

import com.ithirteeng.superfitproject.pushups.presentation.PushUpsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pushUpsModule = module {
    viewModel {
        PushUpsScreenViewModel(
            getPushUpsAmountUseCase = get(),
            setPushUpsAmountUseCase = get(),
            saveTrainingUseCase = get(),
            addExerciseUseCase = get()
        )
    }
}