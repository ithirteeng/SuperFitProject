package com.ithirteeng.superfitproject.common.network.di

import com.ithirteeng.superfitproject.common.network.service.authenticator.TokenAuthenticator
import com.ithirteeng.superfitproject.common.network.service.interceptor.AuthInterceptor
import com.ithirteeng.superfitproject.common.network.service.okhttp.setupOkHttpClient
import com.ithirteeng.superfitproject.common.network.service.okhttp.setupTokenOkHttpClient
import com.ithirteeng.superfitproject.common.network.service.provideLoggingInterceptor
import com.ithirteeng.superfitproject.common.network.service.provideNetworkConnectionInterceptor
import com.ithirteeng.superfitproject.common.network.service.provideRetrofit
import com.ithirteeng.superfitproject.common.network.utils.SIMP_NETWORK_TOOLS
import com.ithirteeng.superfitproject.common.network.utils.TOKEN_NETWORK_TOOLS
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    factory { provideLoggingInterceptor() }
    factory { provideNetworkConnectionInterceptor(context = get()) }

    single(named(SIMP_NETWORK_TOOLS)) {
        provideRetrofit(okHttpClient = get(named(SIMP_NETWORK_TOOLS)))
    }

    single(named(TOKEN_NETWORK_TOOLS)) {
        provideRetrofit(okHttpClient = get(named(TOKEN_NETWORK_TOOLS)))
    }

    single(named(SIMP_NETWORK_TOOLS)) {
        setupOkHttpClient(
            loggingInterceptor = get(),
            networkConnectionInterceptor = get()
        )
    }

    factory(named(TOKEN_NETWORK_TOOLS)) {
        setupTokenOkHttpClient(
            loggingInterceptor = get(),
            networkConnectionInterceptor = get(),
            authInterceptor = get(),
            tokenAuthenticator = get()
        )
    }

    factory {
        TokenAuthenticator(
            getTokenFromLocalStorageUseCase = get(),
            saveTokenLocallyUseCase = get(),
            refreshTokenUseCase = get()
        )
    }

    factory {
        AuthInterceptor(
            getTokenFromLocalStorageUseCase = get()
        )
    }
}