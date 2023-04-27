package com.ithirteeng.superfitproject.signin.di

import com.ithirteeng.superfitproject.signin.presentation.SignInScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signInModule = module {
    viewModel {
        SignInScreenViewModel()
    }
}