package com.ithirteeng.superfitproject.splash.presentation

import com.ithirteeng.superfitproject.common.entity.ErrorEntity

sealed class SplashState {
    object Loading : SplashState()
    object CompleteCheck : SplashState()
    class Error(errorEntity: ErrorEntity) : SplashState()
}