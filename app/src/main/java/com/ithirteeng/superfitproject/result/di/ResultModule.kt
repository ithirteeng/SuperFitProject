package com.ithirteeng.superfitproject.result.di

import com.ithirteeng.superfitproject.result.presentation.ResultScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val resultModule = module {
    viewModel {
        ResultScreenViewModel()
    }
}