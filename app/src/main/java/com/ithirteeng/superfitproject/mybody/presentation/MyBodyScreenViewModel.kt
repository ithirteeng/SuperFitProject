package com.ithirteeng.superfitproject.mybody.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetWeightAndHeightUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetHeightUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetWeightUseCase
import com.ithirteeng.superfitproject.mybody.domain.usecase.UpdateBodyParamsUseCase
import com.ithirteeng.superfitproject.mybody.presentation.model.AlertDialogType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyBodyScreenViewModel(
    private val application: Application,
    private val getWeightAndHeightUseCase: GetWeightAndHeightUseCase,
    private val updateBodyParamsUseCase: UpdateBodyParamsUseCase,
    private val setWeightUseCase: SetWeightUseCase,
    private val setHeightUseCase: SetHeightUseCase,
) : AndroidViewModel(application) {

    fun accept(myBodyScreenIntent: MyBodyScreenIntent) {
        when (myBodyScreenIntent) {
            is MyBodyScreenIntent.ChangeHeight -> changeHeight()
            is MyBodyScreenIntent.ChangeWeight -> changeWeight()
            is MyBodyScreenIntent.DismissError -> {}
            is MyBodyScreenIntent.Initial -> initState()
            is MyBodyScreenIntent.CloseAlertDialog -> closeAlertDialog()
            is MyBodyScreenIntent.OpenAlertDialog -> openAlertDialog(myBodyScreenIntent.alertDialogType)
            is MyBodyScreenIntent.AlertTextFieldChange -> onTextFieldChange(myBodyScreenIntent.value)
        }
    }

    private val _state = MutableStateFlow(MyBodyScreenState())

    val state: StateFlow<MyBodyScreenState> = _state

    private fun initState() {
        _state.value = MyBodyScreenState(
            weight = getWeightAndHeight().first,
            height = getWeightAndHeight().second
        )
    }

    private fun getWeightAndHeight(): Pair<String, String> {
        val weightAndHeight = getWeightAndHeightUseCase()
        var first = weightAndHeight.first
        var second = weightAndHeight.second
        if (first == null) first = application.resources.getString(R.string.undefined)
        if (second == null) second = application.resources.getString(R.string.undefined)
        return Pair(first, second)
    }

    private fun onTextFieldChange(value: String) {
        _state.value = _state.value.copy(
            alertTextFieldValue = value
        )
    }

    private fun openAlertDialog(alertDialogType: AlertDialogType) {
        _state.value = _state.value.copy(
            isAlertDialogOpened = true,
            alertDialogType = alertDialogType
        )
    }

    private fun closeAlertDialog() {
        _state.value = _state.value.copy(
            alertTextFieldValue = "",
            isAlertDialogOpened = false
        )
    }

    private fun changeWeight() {
        setWeightUseCase(weight = _state.value.alertTextFieldValue.toInt())
        _state.value = _state.value.copy(
            weight = getWeightAndHeight().first,
            // todo isLoading = true
            isAlertDialogOpened = false,
            alertTextFieldValue = ""
        )
    }

    private fun changeHeight() {
        setHeightUseCase(height = _state.value.alertTextFieldValue.toInt())
        _state.value = _state.value.copy(
            height = getWeightAndHeight().second,
            // todo isLoading = true
            isAlertDialogOpened = false,
            alertTextFieldValue = ""
        )
    }


}