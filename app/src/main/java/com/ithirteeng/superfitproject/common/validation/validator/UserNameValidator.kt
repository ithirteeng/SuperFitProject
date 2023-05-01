package com.ithirteeng.superfitproject.common.validation.validator

class UserNameValidator {

    fun validate(value: String): Boolean {
        return value.isNotEmpty()
    }
}