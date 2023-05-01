package com.ithirteeng.superfitproject.signup.presentation

data class SignUpModel(
    val userName: String,
    val email: String,
    val code: String,
    val repeatCode: String,
)
