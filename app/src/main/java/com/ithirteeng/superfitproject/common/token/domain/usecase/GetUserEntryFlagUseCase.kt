package com.ithirteeng.superfitproject.common.token.domain.usecase

import com.ithirteeng.superfitproject.common.token.domain.repository.TokenRepository

class GetUserEntryFlagUseCase(
    private val repository: TokenRepository
) {
    operator fun invoke(): Boolean =
        repository.getUserEntryFlag()
}