package com.ithirteeng.superfitproject.common.token.domain.usecase

import com.ithirteeng.superfitproject.common.token.domain.entity.AuthTokenEntity
import com.ithirteeng.superfitproject.common.token.domain.entity.LoginEntity
import com.ithirteeng.superfitproject.common.token.domain.repository.TokenRepository
import com.ithirteeng.superfitproject.common.utils.provideResult

class GetRefreshTokenUseCase(
    private val repository: TokenRepository,
) {
    suspend operator fun invoke(loginEntity: LoginEntity): Result<AuthTokenEntity> {
        return provideResult {
            repository.getRefreshToken(loginEntity)
        }
    }
}