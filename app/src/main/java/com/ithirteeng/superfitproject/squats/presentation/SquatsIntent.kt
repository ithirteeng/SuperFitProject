package com.ithirteeng.superfitproject.squats.presentation

sealed class SquatsIntent {
    object Initial : SquatsIntent()
    object BackButtonClick : SquatsIntent()
    object ActionIntent : SquatsIntent()
    object DismissErrorDialog: SquatsIntent()
}