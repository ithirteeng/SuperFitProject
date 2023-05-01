package com.ithirteeng.superfitproject.signup.domain.usecase

import android.util.Log
import com.ithirteeng.superfitproject.common.token.domain.entity.LoginEntity
import com.ithirteeng.superfitproject.signup.domain.repository.SignUpRepository

class RegisterUseCase(
    private val repository: SignUpRepository,
) {
    suspend operator fun invoke(loginEntity: LoginEntity): Result<Unit> {
        return try {
            Result.success(repository.register(loginEntity))
        } catch (e: Exception) {
            Log.e("ERROR", "haapen: ", e)
            Result.failure(e)
        }
    }
}