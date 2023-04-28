package com.ithirteeng.superfitproject.signin.presentation

data class SignInState(
    val isLoading: Boolean = true,
    val textFieldValue: String = "",
    val isError: Throwable? = null,
)
