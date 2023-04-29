package com.ithirteeng.superfitproject.common.token.domain.usecase

import com.ithirteeng.superfitproject.common.token.domain.repository.TokenRepository

class SaveCurrentUserNameUseCase(
    private val repository: TokenRepository,
) {
    operator fun invoke(userName: String) =
        repository.saveCurrentUserName(userName = userName)
}