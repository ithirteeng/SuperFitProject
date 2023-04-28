package com.ithirteeng.superfitproject.signin.di

import com.ithirteeng.superfitproject.signin.presentation.first.SignInFirstScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signInModule = module {
    viewModel {
        SignInFirstScreenViewModel()
    }
}