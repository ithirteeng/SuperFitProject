package com.ithirteeng.superfitproject.mybody.presentation

import com.ithirteeng.superfitproject.mybody.presentation.model.AlertDialogType

sealed class MyBodyScreenIntent {
    object Initial : MyBodyScreenIntent()
    class ChangeWeight(val weight: Int) : MyBodyScreenIntent()
    class ChangeHeight(val height: Int) : MyBodyScreenIntent()
    object DismissError : MyBodyScreenIntent()
    object CloseAlertDialog : MyBodyScreenIntent()
    class OpenAlertDialog(val alertDialogType: AlertDialogType) : MyBodyScreenIntent()
    class AlertTextFieldChange(val value: String): MyBodyScreenIntent()
}
