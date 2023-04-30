package com.ithirteeng.superfitproject.common.token.domain.usecase

import com.ithirteeng.superfitproject.common.token.domain.repository.TokenRepository

class RemoveCurrentUserNameUseCase(
    private val repository: TokenRepository,
) {
    operator fun invoke() =
        repository.removeCurrentUserName()
}