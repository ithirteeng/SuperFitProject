package com.ithirteeng.superfitproject.signin.presentation.first

import com.ithirteeng.superfitproject.common.entity.ErrorEntity

data class SignInFirstState(
    val isLoading: Boolean = true,
    val userName: String = "",
    val error: ErrorEntity? = null,
    val isCompleted: Boolean = false
)
