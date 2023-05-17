package com.ithirteeng.superfitproject.crunch.presentation

sealed class CrunchScreenIntent {

    object Initial : CrunchScreenIntent()

    object FinishButtonClick : CrunchScreenIntent()

    object DismissErrorDialog : CrunchScreenIntent()

    object BackButtonClick : CrunchScreenIntent()
}
