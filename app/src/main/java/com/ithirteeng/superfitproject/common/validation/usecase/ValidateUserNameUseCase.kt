package com.ithirteeng.superfitproject.common.validation.usecase

import com.ithirteeng.superfitproject.common.validation.validator.UserNameValidator

class ValidateUserNameUseCase(
    private val validator: UserNameValidator
) {
    operator fun invoke(value: String): Boolean =
        validator.validate(value)
}