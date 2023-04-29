package com.ithirteeng.superfitproject.signin.di

import com.ithirteeng.superfitproject.signin.presentation.first.SignInFirstScreenViewModel
import com.ithirteeng.superfitproject.signin.presentation.second.SignInSecondScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val SIGN_IN_FIRST_VIEW_MODEL = "SIGN_IN_FIRST_VIEW_MODEL"
const val SIGN_IN_SECOND_VIEW_MODEL = "SIGN_IN_SECOND_VIEW_MODEL"

val signInModule = module {

    viewModel(named(SIGN_IN_FIRST_VIEW_MODEL)) {
        SignInFirstScreenViewModel(
            saveCurrentUserNameUseCase = get()
        )
    }

    viewModel(named(SIGN_IN_SECOND_VIEW_MODEL)) {
        SignInSecondScreenViewModel()
    }

}