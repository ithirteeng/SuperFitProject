package com.ithirteeng.superfitproject.signup.di

import com.ithirteeng.superfitproject.common.network.utils.SIMP_NETWORK_TOOLS
import com.ithirteeng.superfitproject.common.network.utils.createRetrofitService
import com.ithirteeng.superfitproject.signup.data.api.SignUpApi
import com.ithirteeng.superfitproject.signup.data.repository.SignUpRepositoryImpl
import com.ithirteeng.superfitproject.signup.domain.repository.SignUpRepository
import com.ithirteeng.superfitproject.signup.domain.usecase.RegisterUseCase
import com.ithirteeng.superfitproject.signup.presentation.SignUpScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val signUpModule = module {
    single { createRetrofitService<SignUpApi>(retrofit = get(named(SIMP_NETWORK_TOOLS))) }

    factory<SignUpRepository> { SignUpRepositoryImpl(api = get()) }
    factory { RegisterUseCase(repository = get()) }

    viewModel {
        SignUpScreenViewModel(
            validateEmailUseCase = get(),
            validateUserNameUseCase = get(),
            validateCodeUseCase = get(),
            validateRepeatCodeUseCase = get(),
            getAccessTokenUseCase = get(),
            getRefreshTokenUseCase = get(),
            saveTokenLocallyUseCase = get(),
            registerUseCase = get(),
        )
    }
}