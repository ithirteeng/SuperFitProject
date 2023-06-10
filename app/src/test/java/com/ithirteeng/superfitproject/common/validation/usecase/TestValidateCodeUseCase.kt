package com.ithirteeng.superfitproject.common.validation.usecase

import com.ithirteeng.superfitproject.common.validation.validator.CodeValidator
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class TestValidateCodeUseCase {

    private val validator: CodeValidator = mock()
    private val useCase = ValidateCodeUseCase(validator)

    @Test
    fun `test invocation EXPECT complete`() {
        val code = "1235"
        whenever(validator.validate(any())) doReturn true
        assertEquals(true, useCase.invoke(code))
    }
}