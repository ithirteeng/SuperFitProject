package com.ithirteeng.superfitproject.signup.data.api

import com.ithirteeng.superfitproject.common.token.domain.entity.LoginEntity
import retrofit2.http.POST

interface SignUpApi {
    @POST("auth/register")
    suspend fun register(loginEntity: LoginEntity)
}