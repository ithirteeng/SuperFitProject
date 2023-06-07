package com.ithirteeng.superfitproject.result.presentation

sealed class ResultIntent {
    object Initial : ResultIntent()
    object GoHomeButtonClick : ResultIntent()
}