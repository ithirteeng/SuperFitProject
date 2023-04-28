package com.ithirteeng.superfitproject.signin.presentation.second

import com.ithirteeng.superfitproject.common.entity.ErrorEntity

data class SignInSecondState(
    val isLoading: Boolean = true,
    val password: String = "",
    val error: ErrorEntity? = null
)