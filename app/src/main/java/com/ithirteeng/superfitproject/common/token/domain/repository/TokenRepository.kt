package com.ithirteeng.superfitproject.common.token.domain.repository

import com.ithirteeng.superfitproject.common.token.domain.entity.AccessTokenEntity
import com.ithirteeng.superfitproject.common.token.domain.entity.AuthTokenEntity
import com.ithirteeng.superfitproject.common.token.domain.entity.LoginEntity
import com.ithirteeng.superfitproject.common.token.domain.entity.RefreshTokenEntity
import com.ithirteeng.superfitproject.common.token.domain.entity.TokenEntity

interface TokenRepository {

    suspend fun getRefreshToken(loginEntity: LoginEntity): AuthTokenEntity

    suspend fun getAccessToken(refreshTokenEntity: RefreshTokenEntity): AccessTokenEntity

    fun saveToken(tokenEntity: TokenEntity)

    fun getToken(): TokenEntity
}