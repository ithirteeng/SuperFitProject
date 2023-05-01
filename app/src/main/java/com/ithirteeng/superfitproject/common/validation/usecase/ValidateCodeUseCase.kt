package com.ithirteeng.superfitproject.common.validation.usecase

import com.ithirteeng.superfitproject.common.validation.validator.CodeValidator

class ValidateCodeUseCase(
    private val validator: CodeValidator,
) {
    operator fun invoke(value: String): Boolean =
        validator.validate(value)
}