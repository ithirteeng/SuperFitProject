package com.ithirteeng.superfitproject.statistics.data.api

import com.ithirteeng.superfitproject.statistics.domain.entity.BodyParamsEntity
import retrofit2.http.GET

interface StatisticsApi {
    @GET("profile/params/history")
    suspend fun getUserParams(): List<BodyParamsEntity>
}