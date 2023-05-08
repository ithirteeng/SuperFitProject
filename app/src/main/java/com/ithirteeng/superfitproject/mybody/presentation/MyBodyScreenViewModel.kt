package com.ithirteeng.superfitproject.mybody.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ithirteeng.superfitproject.R
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.GetWeightAndHeightUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetHeightUseCase
import com.ithirteeng.superfitproject.common.exercises.domain.usecase.SetWeightUseCase
import com.ithirteeng.superfitproject.mybody.domain.usecase.UpdateBodyParamsUseCase
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
            is MyBodyScreenIntent.ChangeHeight -> changeHeight(myBodyScreenIntent.height)
            is MyBodyScreenIntent.ChangeWeight -> changeWeight(myBodyScreenIntent.weight)
            is MyBodyScreenIntent.DismissError -> {}
            is MyBodyScreenIntent.Initial -> initState()
            is MyBodyScreenIntent.CloseAlertDialog -> closeAlertDialog()
            is MyBodyScreenIntent.OpenAlertDialog -> openAlertDialog()
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

    private fun openAlertDialog() {
        _state.value = _state.value.copy(
            isAlertDialogOpened = true
        )
    }

    private fun closeAlertDialog() {
        _state.value = _state.value.copy(
            isAlertDialogOpened = false
        )
    }

    private fun changeWeight(weight: Number) {
        setWeightUseCase(weight = weight)
        _state.value = _state.value.copy(
            weight = getWeightAndHeight().first,
            // todo isLoading = true
            isAlertDialogOpened = false
        )
    }

    private fun changeHeight(height: Number) {
        setHeightUseCase(height = height)
        _state.value = _state.value.copy(
            weight = getWeightAndHeight().second,
            // todo isLoading = true
            isAlertDialogOpened = false
        )
    }


}