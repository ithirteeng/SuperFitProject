package com.ithirteeng.superfitproject.exerciseslist.di

import com.ithirteeng.superfitproject.exerciseslist.presentation.ExercisesScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val exercisesScreenModule = module {
    viewModel {
        ExercisesScreenViewModel()
    }
}