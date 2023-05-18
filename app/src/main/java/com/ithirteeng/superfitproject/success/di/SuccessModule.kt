package com.ithirteeng.superfitproject.success.di

import com.ithirteeng.superfitproject.success.presentation.SuccessScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val successModule = module {
    viewModel {
        SuccessScreenViewModel()
    }
}