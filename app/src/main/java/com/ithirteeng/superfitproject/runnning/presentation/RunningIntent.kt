package com.ithirteeng.superfitproject.runnning.presentation

sealed class RunningIntent {
    object Initial : RunningIntent()
    object FinishButtonClick : RunningIntent()
    object DismissErrorDialog : RunningIntent()
    object ActionIntent : RunningIntent()
}
