package com.ithirteeng.superfitproject.signup.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity

data class SignUpState(
    val isLoading: Boolean,
    val error: ErrorEntity,
    val data: SignUpModel,
)