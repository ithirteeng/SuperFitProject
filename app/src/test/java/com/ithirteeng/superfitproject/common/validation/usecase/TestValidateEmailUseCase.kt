package com.ithirteeng.superfitproject.common.validation.usecase

import com.ithirteeng.superfitproject.common.validation.validator.EmailValidator
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class TestValidateEmailUseCase {

    private val validator: EmailValidator = mock()
    private val useCase = ValidateEmailUseCase(validator)

    @Test
    fun `test invocation EXPECT complete`() {
        val email = "email@email.email"
        whenever(validator.validate(any())) doReturn true
        assertEquals(true, useCase.invoke(email))
    }

}