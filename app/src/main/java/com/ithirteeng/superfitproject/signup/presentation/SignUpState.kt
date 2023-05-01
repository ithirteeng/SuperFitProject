package com.ithirteeng.superfitproject.signup.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity
import com.ithirteeng.superfitproject.signup.presentation.model.CompletionModel
import com.ithirteeng.superfitproject.signup.presentation.model.SignUpModel

data class SignUpState(
    val isLoading: Boolean = false,
    val error: ErrorEntity? = null,
    val data: SignUpModel = SignUpModel(),
    val completionModel: CompletionModel = CompletionModel(),
)