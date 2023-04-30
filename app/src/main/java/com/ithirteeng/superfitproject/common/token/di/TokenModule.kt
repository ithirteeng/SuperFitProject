package com.ithirteeng.superfitproject.common.token.di

import com.ithirteeng.superfitproject.common.network.utils.SIMP_NETWORK_TOOLS
import com.ithirteeng.superfitproject.common.network.utils.createRetrofitService
import com.ithirteeng.superfitproject.common.token.data.api.TokenApi
import com.ithirteeng.superfitproject.common.token.data.repository.TokenRepositoryImpl
import com.ithirteeng.superfitproject.common.token.data.storage.TokenStorage
import com.ithirteeng.superfitproject.common.token.domain.repository.TokenRepository
import com.ithirteeng.superfitproject.common.token.domain.usecase.GetAccessTokenUseCase
import com.ithirteeng.superfitproject.common.token.domain.usecase.GetCurrentUserNameUseCase
import com.ithirteeng.superfitproject.common.token.domain.usecase.GetRefreshTokenUseCase
import com.ithirteeng.superfitproject.common.token.domain.usecase.GetTokenFromLocalStorageUseCase
import com.ithirteeng.superfitproject.common.token.domain.usecase.RefreshTokenUseCase
import com.ithirteeng.superfitproject.common.token.domain.usecase.RemoveCurrentUserNameUseCase
import com.ithirteeng.superfitproject.common.token.domain.usecase.SaveCurrentUserNameUseCase
import com.ithirteeng.superfitproject.common.token.domain.usecase.SaveTokenLocallyUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val tokenModule = module {
    single { createRetrofitService<TokenApi>(retrofit = get(named(SIMP_NETWORK_TOOLS))) }
    single { TokenStorage(context = get()) }

    factory<TokenRepository> { TokenRepositoryImpl(api = get(), storage = get()) }

    factory { GetAccessTokenUseCase(repository = get()) }
    factory { GetRefreshTokenUseCase(repository = get()) }
    factory { GetTokenFromLocalStorageUseCase(repository = get()) }
    factory { SaveTokenLocallyUseCase(repository = get()) }
    factory { SaveCurrentUserNameUseCase(repository = get()) }
    factory { GetCurrentUserNameUseCase(repository = get()) }
    factory { RemoveCurrentUserNameUseCase(repository = get()) }
    factory {
        RefreshTokenUseCase(
            getRefreshTokenUseCase = get(),
            getAccessTokenUseCase = get()
        )
    }
}