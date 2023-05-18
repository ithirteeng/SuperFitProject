package com.ithirteeng.superfitproject.plank.presentation

sealed class PlankIntent {
    object Initial : PlankIntent()
    object BackButtonClick : PlankIntent()
    object FinishButtonClick : PlankIntent()
    object DismissErrorDialog : PlankIntent()
    object GoButtonClick : PlankIntent()
    object LaterButtonClick : PlankIntent()
    object FinishExercise : PlankIntent()
}