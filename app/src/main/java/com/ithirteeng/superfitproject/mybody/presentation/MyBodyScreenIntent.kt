package com.ithirteeng.superfitproject.mybody.presentation

import com.ithirteeng.superfitproject.mybody.presentation.model.AlertDialogType

sealed class MyBodyScreenIntent {
    object Initial : MyBodyScreenIntent()
    object ChangeWeight : MyBodyScreenIntent()
    object ChangeHeight : MyBodyScreenIntent()
    object DismissError : MyBodyScreenIntent()
    object CloseAlertDialog : MyBodyScreenIntent()
    class OpenAlertDialog(val alertDialogType: AlertDialogType) : MyBodyScreenIntent()
    class AlertTextFieldChange(val value: String): MyBodyScreenIntent()
}
