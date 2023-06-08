package com.ithirteeng.superfitproject.runnning.presentation

sealed class RunningIntent {
    object Initial : RunningIntent()
    object FinishButtonClick : RunningIntent()
    object DismissErrorDialog : RunningIntent()
    class ActionIntent(val steps: Int) : RunningIntent()
    class SetDefaultSteps(val steps: Int) : RunningIntent()
}
