package com.ithirteeng.superfitproject.common.validation.validator

import org.junit.Assert.assertEquals
import org.junit.Test

class TestRepeatCodeValidator {

    private val validator = RepeatCodeValidator()

    @Test
    fun `test equal codes EXPECT true`() {
        val firstCode = "1234"
        val secondCode = "1234"
        assertEquals(true, validator.validate(firstCode, secondCode))
    }

    @Test
    fun `test not equal codes EXPECT false`() {
        val firstCode = "8268"
        val secondCode = "1234"
        assertEquals(false, validator.validate(firstCode, secondCode))
    }
}