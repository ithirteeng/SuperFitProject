package com.ithirteeng.superfitproject.statistics.domain.usecase

import com.ithirteeng.superfitproject.common.utils.provideResult
import com.ithirteeng.superfitproject.statistics.domain.entity.BodyParamsEntity
import com.ithirteeng.superfitproject.statistics.domain.repository.StatisticsRepository

class GetBodyParamsHistoryUseCase(
    private val repository: StatisticsRepository,
) {
    suspend operator fun invoke(): Result<List<BodyParamsEntity>> {
        return provideResult { repository.getBodyParamsHistory() }
    }
}