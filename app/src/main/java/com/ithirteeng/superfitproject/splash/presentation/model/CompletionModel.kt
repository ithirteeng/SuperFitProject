package com.ithirteeng.superfitproject.splash.presentation.model

data class CompletionModel(
    val isCompleted: Boolean = false,
    val nextScreenType: SplashNextScreenType = SplashNextScreenType.REGISTRATION,
)
