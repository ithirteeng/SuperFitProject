package com.ithirteeng.superfitproject.splash.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity
import com.ithirteeng.superfitproject.splash.presentation.model.CompletionModel

data class SplashState(
    val isLoading: Boolean = false,
    val completionModel: CompletionModel = CompletionModel(),
    val userEmail: String? = null,
    val error: ErrorEntity? = null,
)

