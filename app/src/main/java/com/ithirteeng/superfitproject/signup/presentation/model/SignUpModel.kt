package com.ithirteeng.superfitproject.signup.presentation.model

data class SignUpModel(
    val userName: String = "",
    val email: String = "",
    val code: String = "",
    val repeatCode: String = "",
)
