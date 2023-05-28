package com.ithirteeng.superfitproject.mybody.presentation

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetWeightAndHeightUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetHeightUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetWeightUseCase
import com.ithirteeng.superfitproject.common.utils.ErrorHelper
import com.ithirteeng.superfitproject.mybody.domain.entity.BodyParamsEntity
import com.ithirteeng.superfitproject.mybody.domain.usecase.UpdateBodyParamsUseCase
import com.ithirteeng.superfitproject.mybody.presentation.model.AlertDialogType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

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
            is MyBodyScreenIntent.DismissError -> dismissError()
            is MyBodyScreenIntent.Initial -> initState()
            is MyBodyScreenIntent.CloseAlertDialog -> closeAlertDialog()
            is MyBodyScreenIntent.OpenAlertDialog -> openAlertDialog(myBodyScreenIntent.alertDialogType)
            is MyBodyScreenIntent.AlertTextFieldChange -> onTextFieldChange(myBodyScreenIntent.value)
        }
    }

    private val _state = MutableStateFlow(MyBodyScreenState())

    val state: StateFlow<MyBodyScreenState> = _state

    private fun dismissError() {
        _state.value = _state.value.copy(
            error = null,
            isLoading = false
        )
    }

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
            isLoading = false,
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
        if (_state.value.alertTextFieldValue.isNotEmpty()) {
            if (getWeightAndHeightUseCase().second != null) {
                uploadBodyParams(
                    weight = setupParam(_state.value.alertTextFieldValue),
                    height = setupParam(getWeightAndHeightUseCase().second)
                ) {
                    _state.value = _state.value.copy(
                        weight = getWeightAndHeight().first,
                    )
                }
                _state.value = _state.value.copy(
                    isAlertDialogOpened = false,
                    alertTextFieldValue = ""
                )
            } else {
                setWeightUseCase(weight = _state.value.alertTextFieldValue.toInt())
                _state.value = _state.value.copy(
                    isAlertDialogOpened = false,
                    alertTextFieldValue = "",
                    weight = getWeightAndHeight().first
                )
            }
        }
    }

    private fun changeHeight() {
        if (_state.value.alertTextFieldValue.isNotEmpty()) {
            if (getWeightAndHeightUseCase().first != null) {

                uploadBodyParams(
                    height = setupParam(_state.value.alertTextFieldValue),
                    weight = setupParam(getWeightAndHeightUseCase().first)
                ) {
                    _state.value = _state.value.copy(
                        height = getWeightAndHeight().second
                    )
                }
                _state.value = _state.value.copy(
                    isAlertDialogOpened = false,
                    alertTextFieldValue = ""
                )
            } else {
                setHeightUseCase(height = _state.value.alertTextFieldValue.toInt())
                _state.value = _state.value.copy(
                    isAlertDialogOpened = false,
                    alertTextFieldValue = "",
                    height = getWeightAndHeight().second
                )
            }
        }
    }

    private fun setupParam(param: String?): String? {
        return if (param == null) {
            null
        } else if (param.split(" ")[0].isNotEmpty()) {
            param.split(" ")[0]
        } else {
            param
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun uploadBodyParams(weight: String?, height: String?, onSuccess: () -> Unit) {
        if (weight != null && height != null) {
            _state.value = _state.value.copy(
                isLoading = true
            )
            viewModelScope.launch {
                updateBodyParamsUseCase(
                    BodyParamsEntity(
                        weight = weight,
                        height = height,
                        date = SimpleDateFormat("yyyy-MM-dd").format(Date())
                    )
                ).onSuccess {
                    setHeightUseCase(height = height.split(" ")[0].toInt())
                    setWeightUseCase(weight = weight.split(" ")[0].toInt())
                    onSuccess()
                    _state.value = _state.value.copy(
                        isLoading = false
                    )
                }.onFailure {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = ErrorHelper.setupErrorEntity(it, R.string.uploading_params_error),
                        alertTextFieldValue = ""
                    )
                }
            }
        }
    }


}