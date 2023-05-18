package com.ithirteeng.superfitproject.success.presentation

sealed class SuccessIntent {
    object Initial : SuccessIntent()
    object GoHomeButtonClick : SuccessIntent()
}