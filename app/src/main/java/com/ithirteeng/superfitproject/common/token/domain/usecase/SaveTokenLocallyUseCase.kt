package com.ithirteeng.superfitproject.common.token.domain.usecase

import com.ithirteeng.superfitproject.common.token.domain.entity.TokenEntity
import com.ithirteeng.superfitproject.common.token.domain.repository.TokenRepository

class SaveTokenLocallyUseCase(
    private val repository: TokenRepository,
) {
    operator fun invoke(tokenEntity: TokenEntity) =
        repository.saveToken(tokenEntity)
}