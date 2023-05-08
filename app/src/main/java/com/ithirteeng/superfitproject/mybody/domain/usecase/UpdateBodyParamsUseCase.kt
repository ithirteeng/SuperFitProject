package com.ithirteeng.superfitproject.mybody.domain.usecase

import com.ithirteeng.superfitproject.common.utils.provideResult
import com.ithirteeng.superfitproject.mybody.domain.entity.BodyParamsEntity
import com.ithirteeng.superfitproject.mybody.domain.repository.MyBodyRepository

class UpdateBodyParamsUseCase(
    private val repository: MyBodyRepository,
) {
    suspend operator fun invoke(bodyParamsEntity: BodyParamsEntity): Result<Unit> {
        return provideResult {
            repository.updateParams(bodyParamsEntity = bodyParamsEntity)
        }
    }
}