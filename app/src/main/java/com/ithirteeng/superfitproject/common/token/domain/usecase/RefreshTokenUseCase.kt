package com.ithirteeng.superfitproject.common.token.domain.usecase

import com.ithirteeng.superfitproject.common.token.domain.entity.LoginEntity

class RefreshTokenUseCase(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getRefreshTokenUseCase: GetRefreshTokenUseCase,
) {
    suspend operator fun invoke(loginEntity: LoginEntity): String? {
        getRefreshTokenUseCase(loginEntity = loginEntity)
            .onSuccess {
                getAccessTokenUseCase(it.refreshToken)
                    .onSuccess { token ->
                        return token.accessToken
                    }
                    .onFailure {
                        return null
                    }

            }
            .onFailure {
                return null
            }
        return null
    }
}