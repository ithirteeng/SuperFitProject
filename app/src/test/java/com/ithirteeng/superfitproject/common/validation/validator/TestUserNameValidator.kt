package com.ithirteeng.superfitproject.common.validation.validator

import org.junit.Assert.assertEquals
import org.junit.Test

class TestUserNameValidator {
    private val validator = UserNameValidator()

    @Test
    fun `test correct username EXPECT true`() {
        assertEquals(true, validator.validate("username"))
    }

    @Test
    fun `test incorrect username empty EXPECT false`() {
        assertEquals(false, validator.validate(""))
    }
}