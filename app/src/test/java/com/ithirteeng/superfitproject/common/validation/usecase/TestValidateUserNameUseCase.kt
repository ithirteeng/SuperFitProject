package com.ithirteeng.superfitproject.common.validation.usecase

import com.ithirteeng.superfitproject.common.validation.validator.UserNameValidator
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class TestValidateUserNameUseCase {

    private val validator: UserNameValidator = mock()
    private val useCase = ValidateUserNameUseCase(validator)

    @Test
    fun `test invocation EXPECT complete`() {
        whenever(validator.validate(any())) doReturn true
        assertEquals(true, useCase.invoke("username"))
    }

}