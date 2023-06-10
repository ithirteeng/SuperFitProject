package com.ithirteeng.superfitproject.common.validation.validator

import org.junit.Assert.assertEquals
import org.junit.Test

class TestCodeValidator {
    private val validator = CodeValidator()

    @Test
    fun `test correct code EXPECT true`() {
        val code = "1234"
        assertEquals(true, validator.validate(code))
    }

    @Test
    fun `test incorrect code length greater than 4 EXPECT false`() {
        val code = "21345"
        assertEquals(false, validator.validate(code))
    }

    @Test
    fun `test incorrect code length less than 4 EXPECT false`() {
        val code = "215"
        assertEquals(false, validator.validate(code))
    }


}