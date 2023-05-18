package com.ithirteeng.superfitproject.pushups.presentation

sealed class PushUpsIntent {
    object Initial : PushUpsIntent()
    object FinishButtonClick : PushUpsIntent()
    object DismissErrorDialog : PushUpsIntent()
    object ActionIntent: PushUpsIntent()
}