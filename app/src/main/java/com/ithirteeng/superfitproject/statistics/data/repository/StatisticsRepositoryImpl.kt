package com.ithirteeng.superfitproject.statistics.data.repository

import com.ithirteeng.superfitproject.statistics.data.api.StatisticsApi
import com.ithirteeng.superfitproject.statistics.domain.entity.BodyParamsEntity
import com.ithirteeng.superfitproject.statistics.domain.repository.StatisticsRepository

class StatisticsRepositoryImpl(
    private val api: StatisticsApi,
) : StatisticsRepository {
    override suspend fun getBodyParamsHistory(): List<BodyParamsEntity> {
        return api.getUserParams()
    }
}