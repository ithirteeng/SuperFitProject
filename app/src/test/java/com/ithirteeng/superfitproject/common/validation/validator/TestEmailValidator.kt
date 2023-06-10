package com.ithirteeng.superfitproject.common.validation.validator

import org.junit.Assert.assertEquals
import org.junit.Test

class TestEmailValidator {

    private val validator = EmailValidator()

    @Test
    fun `test correct email EXPECT true`() {
        val correctEmail = "email@email.email"
        assertEquals(true, validator.validate(correctEmail))
    }

    @Test
    fun `test incorrect email without AT EXPECT false`() {
        val incorrectEmailWithoutAt = "emailemail.email"
        assertEquals(false, validator.validate(incorrectEmailWithoutAt))
    }

    @Test
    fun `test incorrect email without dot EXPECT false`() {
        val incorrectEmailWithoutDot = "email@emailemail"
        assertEquals(false, validator.validate(incorrectEmailWithoutDot))
    }

    @Test
    fun `test incorrect email empty EXPECT false`() {
        val incorrectEmail = ""
        assertEquals(false, validator.validate(incorrectEmail))
    }
}