package com.ithirteeng.superfitproject.signup.data.repository

import com.ithirteeng.superfitproject.common.token.domain.entity.LoginEntity
import com.ithirteeng.superfitproject.signup.data.api.SignUpApi
import com.ithirteeng.superfitproject.signup.domain.repository.SignUpRepository

class SignUpRepositoryImpl(
    private val api: SignUpApi,
) : SignUpRepository {
    override suspend fun register(loginEntity: LoginEntity) {
        api.register(loginEntity)
    }
}