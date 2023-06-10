package com.ithirteeng.superfitproject.common.validation.usecase

import com.ithirteeng.superfitproject.common.validation.validator.RepeatCodeValidator
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class TestValidateRepeatCodeUseCase {

    private val validator: RepeatCodeValidator = mock()
    private val useCase = ValidateRepeatCodeUseCase(validator)

    @Test
    fun `test invocation EXPECT complete`() {
        whenever(validator.validate(any(), any())) doReturn true
        assertEquals(true, useCase.invoke("1234", "1234"))
    }

}