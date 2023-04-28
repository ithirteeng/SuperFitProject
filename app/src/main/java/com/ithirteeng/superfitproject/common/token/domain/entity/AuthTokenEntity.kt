package com.ithirteeng.superfitproject.common.token.domain.entity

import com.google.gson.annotations.SerializedName

data class AuthTokenEntity(
    @SerializedName("username")
    val userName: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("expired")
    val expired: String
)
