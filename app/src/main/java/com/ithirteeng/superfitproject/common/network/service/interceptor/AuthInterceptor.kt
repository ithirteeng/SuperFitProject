package com.ithirteeng.superfitproject.common.network.service.interceptor

import com.ithirteeng.superfitproject.common.network.utils.AUTHORIZATION_HEADER
import com.ithirteeng.superfitproject.common.network.utils.BEARER
import com.ithirteeng.superfitproject.common.token.domain.usecase.GetTokenFromLocalStorageUseCase
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        if (request.header(AUTHORIZATION_HEADER) == null) {
            getTokenFromLocalStorageUseCase().let {
                builder.addHeader(
                    AUTHORIZATION_HEADER,
                    "$BEARER ${it.accessToken}"
                )
            }
        }

        return chain.proceed(builder.build())
    }
}