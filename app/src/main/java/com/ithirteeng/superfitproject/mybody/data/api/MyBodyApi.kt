package com.ithirteeng.superfitproject.mybody.data.api

import com.ithirteeng.superfitproject.mybody.domain.entity.BodyParamsEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface MyBodyApi {
    @POST("profile/params")
    suspend fun updateParams(@Body bodyParamsEntity: BodyParamsEntity)
}