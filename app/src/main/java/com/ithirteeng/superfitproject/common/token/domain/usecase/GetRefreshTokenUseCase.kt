package com.ithirteeng.superfitproject.common.token.domain.usecase

import com.ithirteeng.superfitproject.common.token.domain.entity.AuthTokenEntity
import com.ithirteeng.superfitproject.common.token.domain.entity.LoginEntity
import com.ithirteeng.superfitproject.common.token.domain.repository.TokenRepository

class GetRefreshTokenUseCase(
    private val repository: TokenRepository,
) {
    suspend operator fun invoke(loginEntity: LoginEntity): Result<AuthTokenEntity> {
        return try {
            Result.success(repository.getRefreshToken(loginEntity))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}