package com.ithirteeng.superfitproject.signup.domain.usecase

import com.ithirteeng.superfitproject.common.token.domain.entity.LoginEntity
import com.ithirteeng.superfitproject.common.utils.provideResult
import com.ithirteeng.superfitproject.signup.domain.repository.SignUpRepository

class RegisterUseCase(
    private val repository: SignUpRepository,
) {
    suspend operator fun invoke(loginEntity: LoginEntity): Result<Unit> {
        return provideResult {
            repository.register(loginEntity)
        }
    }
}