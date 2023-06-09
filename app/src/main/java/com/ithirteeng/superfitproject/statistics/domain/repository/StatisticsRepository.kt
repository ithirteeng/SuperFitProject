package com.ithirteeng.superfitproject.statistics.domain.repository

import com.ithirteeng.superfitproject.statistics.domain.entity.BodyParamsEntity

interface StatisticsRepository {
    suspend fun getBodyParamsHistory(): List<BodyParamsEntity>
}