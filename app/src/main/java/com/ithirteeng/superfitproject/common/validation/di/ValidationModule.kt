package com.ithirteeng.superfitproject.common.validation.di

import com.ithirteeng.superfitproject.common.validation.usecase.ValidateCodeUseCase
import com.ithirteeng.superfitproject.common.validation.usecase.ValidateEmailUseCase
import com.ithirteeng.superfitproject.common.validation.usecase.ValidateRepeatCodeUseCase
import com.ithirteeng.superfitproject.common.validation.usecase.ValidateUserNameUseCase
import com.ithirteeng.superfitproject.common.validation.validator.CodeValidator
import com.ithirteeng.superfitproject.common.validation.validator.EmailValidator
import com.ithirteeng.superfitproject.common.validation.validator.RepeatCodeValidator
import com.ithirteeng.superfitproject.common.validation.validator.UserNameValidator
import org.koin.dsl.module

val validationModule = module {
    factory { CodeValidator() }
    factory { EmailValidator() }
    factory { UserNameValidator() }
    factory { RepeatCodeValidator() }

    factory { ValidateCodeUseCase(validator = get()) }
    factory { ValidateEmailUseCase(validator = get()) }
    factory { ValidateUserNameUseCase(validator = get()) }
    factory { ValidateRepeatCodeUseCase(validator = get()) }
}