package com.ithirteeng.superfitproject.image.presentation

sealed class ImageIntent {
    object BackButtonClick : ImageIntent()
    object Initial : ImageIntent()
}