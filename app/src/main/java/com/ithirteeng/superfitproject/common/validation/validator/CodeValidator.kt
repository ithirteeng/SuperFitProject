package com.ithirteeng.superfitproject.common.validation.validator

class CodeValidator {

    private val regex = Regex("\\d{4}")

    fun validate(value: String): Boolean {
        return value.length == 4 && value.matches(regex = regex)
    }
}