package com.ithirteeng.superfitproject.common.token.domain.usecase

import com.ithirteeng.superfitproject.common.token.domain.repository.TokenRepository

class SetUserEntryFlagUseCase(
    private val repository: TokenRepository
) {
    operator fun invoke(flag: Boolean) =
        repository.setUserEntryFlag(flag)
}