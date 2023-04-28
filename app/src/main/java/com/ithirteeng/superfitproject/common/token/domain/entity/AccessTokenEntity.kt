package com.ithirteeng.superfitproject.common.token.domain.entity

import com.google.gson.annotations.SerializedName

data class AccessTokenEntity(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expired")
    val expired: String
)
