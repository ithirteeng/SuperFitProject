package com.ithirteeng.superfitproject.common.token.data.api

import com.ithirteeng.superfitproject.common.token.domain.entity.AccessTokenEntity
import com.ithirteeng.superfitproject.common.token.domain.entity.AuthTokenEntity
import com.ithirteeng.superfitproject.common.token.domain.entity.LoginEntity
import com.ithirteeng.superfitproject.common.token.domain.entity.RefreshTokenEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenApi {
    @POST("auth/token")
    suspend fun getRefreshToken(@Body loginEntity: LoginEntity): AuthTokenEntity

    @POST("auth/token/refresh")
    suspend fun getAccessToken(@Body refreshTokenEntity: RefreshTokenEntity): AccessTokenEntity
}