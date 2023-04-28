package com.ithirteeng.superfitproject.signin.presentation.first

import com.ithirteeng.superfitproject.common.entity.ErrorEntity

data class SignInFirstState(
    val isLoading: Boolean = true,
    val textFieldValue: String = "",
    val error: ErrorEntity? = null,
)
