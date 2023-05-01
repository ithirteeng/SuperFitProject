package com.ithirteeng.superfitproject.common.validation.validator

class EmailValidator {
    private val regex = Regex("^[A-Za-z\\d\\-_.]+@[A-Za-z\\d\\-]+\\.[A-Za-z\\d]+\$")

    fun validate(value: String): Boolean {
        return value.matches(regex = regex)
    }
}