package com.ithirteeng.superfitproject.common.token.domain.usecase

import com.ithirteeng.superfitproject.common.token.domain.entity.AccessTokenEntity
import com.ithirteeng.superfitproject.common.token.domain.entity.RefreshTokenEntity
import com.ithirteeng.superfitproject.common.token.domain.repository.TokenRepository
import com.ithirteeng.superfitproject.common.utils.provideResult

class GetAccessTokenUseCase(
    private val repository: TokenRepository,
) {
    suspend operator fun invoke(refreshToken: String): Result<AccessTokenEntity> {
        return provideResult {
            repository.getAccessToken(RefreshTokenEntity(refreshToken))
        }
    }
}