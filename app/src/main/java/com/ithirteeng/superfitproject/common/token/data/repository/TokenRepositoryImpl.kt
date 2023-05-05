package com.ithirteeng.superfitproject.common.token.data.repository

import com.ithirteeng.superfitproject.common.token.data.api.TokenApi
import com.ithirteeng.superfitproject.common.token.data.storage.TokenStorage
import com.ithirteeng.superfitproject.common.token.domain.entity.AccessTokenEntity
import com.ithirteeng.superfitproject.common.token.domain.entity.AuthTokenEntity
import com.ithirteeng.superfitproject.common.token.domain.entity.LoginEntity
import com.ithirteeng.superfitproject.common.token.domain.entity.RefreshTokenEntity
import com.ithirteeng.superfitproject.common.token.domain.entity.TokenEntity
import com.ithirteeng.superfitproject.common.token.domain.repository.TokenRepository

class TokenRepositoryImpl(
    private val api: TokenApi,
    private val storage: TokenStorage,
) : TokenRepository {
    override suspend fun getRefreshToken(loginEntity: LoginEntity): AuthTokenEntity {
        return api.getRefreshToken(loginEntity)
    }

    override suspend fun getAccessToken(refreshTokenEntity: RefreshTokenEntity): AccessTokenEntity {
        return api.getAccessToken(refreshTokenEntity)
    }

    override fun saveToken(tokenEntity: TokenEntity) {
        storage.saveTokenEntity(tokenEntity)
    }

    override fun getToken(): TokenEntity? {
        return storage.getTokenEntity()
    }

    override fun getCurrentUserName(): String? {
        return storage.getCurrentUserName()
    }

    override fun saveCurrentUserName(userName: String) {
        storage.saveCurrentUserName(userName = userName)
    }

    override fun removeCurrentUserName() {
        storage.removeCurrentUserName()
    }

    override fun getUserEntryFlag(): Boolean {
        return storage.getUserEntryFlag()
    }

    override fun setUserEntryFlag(flag: Boolean) {
        storage.setUserEntryFlag(flag = flag)
    }

    override fun clearTokenStorage() {
        storage.clearTokenStorage()
    }


}