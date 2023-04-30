package com.ithirteeng.superfitproject.common.token.domain.entity

data class TokenEntity(
    val userName: String,
    val password: String,
    val accessToken: String? = null,
    val refreshToken: String? = null
)
