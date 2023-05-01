package com.ithirteeng.superfitproject.signup.di

import com.ithirteeng.superfitproject.signup.presentation.SignUpScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signUpModule = module {

    viewModel {
        SignUpScreenViewModel()
    }
}