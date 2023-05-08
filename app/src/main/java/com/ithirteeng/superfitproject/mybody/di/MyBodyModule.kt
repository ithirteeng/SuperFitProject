package com.ithirteeng.superfitproject.mybody.di

import com.ithirteeng.superfitproject.common.network.utils.TOKEN_NETWORK_TOOLS
import com.ithirteeng.superfitproject.common.network.utils.createRetrofitService
import com.ithirteeng.superfitproject.mybody.data.api.MyBodyApi
import com.ithirteeng.superfitproject.mybody.data.repository.MyBodyRepositoryImpl
import com.ithirteeng.superfitproject.mybody.domain.repository.MyBodyRepository
import com.ithirteeng.superfitproject.mybody.domain.usecase.UpdateBodyParamsUseCase
import com.ithirteeng.superfitproject.mybody.presentation.MyBodyScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val myBodyModule = module {

    single { createRetrofitService<MyBodyApi>(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }

    factory<MyBodyRepository> { MyBodyRepositoryImpl(api = get()) }

    factory { UpdateBodyParamsUseCase(repository = get()) }

    viewModel {
        MyBodyScreenViewModel(
            setWeightAndHeightUseCase = get(),
            getWeightAndHeightUseCase = get(),
            updateBodyParamsUseCase = get()
        )
    }
}