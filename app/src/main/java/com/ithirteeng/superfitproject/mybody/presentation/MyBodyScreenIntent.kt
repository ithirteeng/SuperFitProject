package com.ithirteeng.superfitproject.mybody.presentation

sealed class MyBodyScreenIntent {
    object Initial : MyBodyScreenIntent()
    class ChangeWeight(val weight: Int) : MyBodyScreenIntent()
    class ChangeHeight(val height: Int) : MyBodyScreenIntent()
    object DismissError : MyBodyScreenIntent()
    object CloseAlertDialog : MyBodyScreenIntent()
    object OpenAlertDialog : MyBodyScreenIntent()
}
