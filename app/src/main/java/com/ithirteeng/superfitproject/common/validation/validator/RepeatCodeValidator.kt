package com.ithirteeng.superfitproject.common.validation.validator

class RepeatCodeValidator {

    fun validate(firstValue: String, secondValue: String): Boolean {
        return firstValue == secondValue
    }
}