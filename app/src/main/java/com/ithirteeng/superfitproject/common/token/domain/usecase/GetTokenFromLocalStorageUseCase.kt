package com.ithirteeng.superfitproject.common.token.domain.usecase

import com.ithirteeng.superfitproject.common.token.domain.entity.TokenEntity
import com.ithirteeng.superfitproject.common.token.domain.repository.TokenRepository

class GetTokenFromLocalStorageUseCase(
    private val repository: TokenRepository,
) {
    operator fun invoke(): TokenEntity? =
        repository.getToken()
}