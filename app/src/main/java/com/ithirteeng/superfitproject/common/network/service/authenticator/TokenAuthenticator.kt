package com.ithirteeng.superfitproject.common.network.service.authenticator

import com.ithirteeng.superfitproject.common.network.utils.AUTHORIZATION_HEADER
import com.ithirteeng.superfitproject.common.network.utils.BEARER
import com.ithirteeng.superfitproject.common.token.domain.entity.LoginEntity
import com.ithirteeng.superfitproject.common.token.domain.usecase.GetTokenFromLocalStorageUseCase
import com.ithirteeng.superfitproject.common.token.domain.usecase.RefreshTokenUseCase
import com.ithirteeng.superfitproject.common.token.domain.usecase.SaveTokenLocallyUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val saveTokenLocallyUseCase: SaveTokenLocallyUseCase,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val localToken = getTokenFromLocalStorageUseCase()

        if (response.responseCount <= 10) {
            try {
                val accessToken = runBlocking {
                    refreshTokenUseCase(LoginEntity(localToken.userName, localToken.password))
                }

                saveTokenLocallyUseCase(
                    localToken.copy(
                        accessToken = accessToken
                    )
                )

            } catch (e: java.lang.Exception) {
                response.request.newBuilder()
                    .authorizationHeader(getTokenFromLocalStorageUseCase().accessToken.toString())
                    .build()
            }
        }

        return if (response.responseCount >= 2) {
            null
        } else {
            response.request.newBuilder()
                .authorizationHeader(getTokenFromLocalStorageUseCase().accessToken.toString())
                .build()
        }

    }

    private val Response.responseCount: Int
        get() = generateSequence(this) { it.priorResponse }.count()

    private fun Request.Builder.authorizationHeader(accessToken: String): Request.Builder {
        return header(
            AUTHORIZATION_HEADER,
            "$BEARER $accessToken"
        )
    }
}