package com.ithirteeng.superfitproject.signin.presentation.second

import com.ithirteeng.superfitproject.common.entity.ErrorEntity

data class SignInSecondState(
    val isLoading: Boolean = true,
    val password: String = "",
    val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
    val error: ErrorEntity? = null,
)