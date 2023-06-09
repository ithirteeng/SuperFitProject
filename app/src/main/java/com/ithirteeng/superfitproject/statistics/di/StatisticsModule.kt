package com.ithirteeng.superfitproject.statistics.di

import com.ithirteeng.superfitproject.common.network.utils.TOKEN_NETWORK_TOOLS
import com.ithirteeng.superfitproject.common.network.utils.createRetrofitService
import com.ithirteeng.superfitproject.statistics.data.api.StatisticsApi
import com.ithirteeng.superfitproject.statistics.data.repository.StatisticsRepositoryImpl
import com.ithirteeng.superfitproject.statistics.domain.repository.StatisticsRepository
import com.ithirteeng.superfitproject.statistics.domain.usecase.GetBodyParamsHistoryUseCase
import com.ithirteeng.superfitproject.statistics.presentation.StatisticsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val statisticsModule = module {
    single { createRetrofitService<StatisticsApi>(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }

    factory<StatisticsRepository> { StatisticsRepositoryImpl(api = get()) }

    factory { GetBodyParamsHistoryUseCase(repository = get()) }

    viewModel {
        StatisticsViewModel(
            getTrainingsUseCase = get(),
            getBodyParamsHistoryUseCase = get()
        )
    }

}