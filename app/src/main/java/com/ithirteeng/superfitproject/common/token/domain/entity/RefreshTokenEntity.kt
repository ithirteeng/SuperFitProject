package com.ithirteeng.superfitproject.common.token.domain.entity

import com.google.gson.annotations.SerializedName

data class RefreshTokenEntity(
    @SerializedName("refresh_token")
    val refreshToken: String
)

