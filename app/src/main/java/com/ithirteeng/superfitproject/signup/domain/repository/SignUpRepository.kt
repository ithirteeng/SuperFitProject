package com.ithirteeng.superfitproject.signup.domain.repository

import com.ithirteeng.superfitproject.common.token.domain.entity.LoginEntity

interface SignUpRepository {
    suspend fun register(loginEntity: LoginEntity)
}