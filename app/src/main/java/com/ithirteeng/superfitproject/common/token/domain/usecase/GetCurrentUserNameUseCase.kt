package com.ithirteeng.superfitproject.common.token.domain.usecase

import com.ithirteeng.superfitproject.common.token.domain.repository.TokenRepository

class GetCurrentUserNameUseCase(
    private val repository: TokenRepository,
) {
    operator fun invoke(): String? =
        repository.getCurrentUserName()
}