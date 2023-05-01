package com.ithirteeng.superfitproject.common.validation.usecase

import com.ithirteeng.superfitproject.common.validation.validator.RepeatCodeValidator

class ValidateRepeatCodeUseCase(
    private val validator: RepeatCodeValidator,
) {
    operator fun invoke(code: String, repeatCode: String): Boolean =
        validator.validate(code, repeatCode)
}