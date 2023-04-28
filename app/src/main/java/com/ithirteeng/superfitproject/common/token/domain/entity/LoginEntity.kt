package com.ithirteeng.superfitproject.common.token.domain.entity

import com.google.gson.annotations.SerializedName

data class LoginEntity(
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String,
)
