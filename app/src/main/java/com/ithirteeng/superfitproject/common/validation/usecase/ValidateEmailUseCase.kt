package com.ithirteeng.superfitproject.common.validation.usecase

import com.ithirteeng.superfitproject.common.validation.validator.EmailValidator

class ValidateEmailUseCase(
    private val validator: EmailValidator,
) {
    operator fun invoke(value: String): Boolean =
        validator.validate(value)
}